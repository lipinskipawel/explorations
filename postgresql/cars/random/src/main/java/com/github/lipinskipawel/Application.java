package com.github.lipinskipawel;

import com.github.lipinskipawel.db.Car;
import com.github.lipinskipawel.db.CarState;
import com.github.lipinskipawel.json.Parser;

import java.net.URI;
import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.github.lipinskipawel.Functionals.unchecked;
import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers.discarding;
import static java.time.Clock.systemUTC;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MICROS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Arrays.stream;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.IntStream.range;

public final class Application {
    private static final List<String> BRANDS = List.of("volvo", "subaru", "citroen", "aston martin", "gmc");
    private static final List<String> MODELS = List.of("abc", "xyz", "444", "9090", "qwerty");

    private static final Parser PARSER = new Parser();

    public static void main(String[] args) {
        final var numberOfCars = parseArgs(args);
        final var randomCars = range(0, numberOfCars)
            .mapToObj(it -> randomCar())
            .map(PARSER::parse);

        try (var httpClient = newHttpClient()) {
            final var requestBuilder = newBuilder().uri(URI.create("http://localhost:8080/cars"));

            randomCars
                .map(it -> requestBuilder.POST(ofString(it)).build())
                .forEach(it -> unchecked(() -> httpClient.send(it, discarding())));
        }
    }

    private static int parseArgs(String[] args) {
        return stream(args)
            .filter(it -> it.startsWith("numCars="))
            .map(it -> it.split("=")[1])
            .map(Integer::valueOf)
            .findFirst()
            .orElse(10);
    }

    private static Car randomCar() {
        return new Car(randomUUID(), randomBrand(), randomModel(), randomCarState(), Optional.of(randomTime()));
    }

    private static String randomBrand() {
        return BRANDS.get(current().nextInt(BRANDS.size()));
    }

    private static String randomModel() {
        return MODELS.get(current().nextInt(MODELS.size()));
    }

    private static CarState randomCarState() {
        final var index = current().nextInt(CarState.values().length);
        return CarState.values()[index];
    }

    private static Instant randomTime() {
        return Clock.tick(systemUTC(), MICROS.getDuration())
            .instant()
            .minus(current().nextInt(60), DAYS)
            .minus(current().nextInt(60), HOURS)
            .minus(current().nextInt(60), MINUTES);
    }
}
