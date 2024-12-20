package com.github.lipinskipawel.json;

import com.github.lipinskipawel.db.Car;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.json;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;

class CarDeserializerTest implements WithAssertions {

    private final Parser parser = new Parser();

    @Test
    void deserialize_all_fields() {
        final var uuid = randomUUID();
        final var currentOwner = randomUUID();
        final var createdDate = Instant.now();
        final var assembledDate = Instant.now();
        final var json = """
            {
              "id": "%s",
              "brand": "volvo",
              "model": "xc60",
              "submodel": "none",
              "wheels": 4,
              "current_owner": "%s",
              "features": ["ac", "audio system"],
              "details": "{}",
              "created_date": "%s",
              "assembled_date": "%s"
            }""".formatted(uuid, currentOwner, createdDate.toString(), assembledDate.toString());

        final var car = parser.parse(json);

        assertThat(car).isEqualTo(new Car(
            uuid,
            "volvo",
            "xc60",
            "none",
            4,
            Optional.of(currentOwner),
            new String[]{"ac", "audio system"},
            "{}",
            createdDate,
            Optional.of(assembledDate)
        ));
    }

    @Test
    void serialize_all_fields() {
        final var car = new Car(
            randomUUID(),
            "volvo",
            "xc60",
            "none",
            4,
            Optional.of(randomUUID()),
            new String[]{"ac", "audio system"},
            "{}",
            Instant.now(),
            Optional.of(Instant.now())
        );

        final var result = parser.parse(car);

        assertThatJson(result)
            .when(IGNORING_ARRAY_ORDER)
            .isEqualTo(json("""
                {
                  "id": "%s",
                  "brand": "volvo",
                  "model": "xc60",
                  "submodel": "none",
                  "wheels": 4,
                  "current_owner": "%s",
                  "features": ["ac", "audio system"],
                  "details": "{}",
                  "created_date": "%s",
                  "assembled_date": "%s"
                }""".formatted(car.id(), car.currentOwner().get(), car.createdDate(), car.assembledData().get())));
    }
}
