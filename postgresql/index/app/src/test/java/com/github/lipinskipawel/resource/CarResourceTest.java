package com.github.lipinskipawel.resource;

import com.github.lipinskipawel.IntegrationSpec;
import com.github.lipinskipawel.db.Car;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.UUID.randomUUID;

final class CarResourceTest
    extends IntegrationSpec
    implements WithAssertions {

    @AfterAll
    static void afterAll() {
        truncateCars();
    }

    @Test
    void save_car() throws IOException, InterruptedException {
        final var volvo = new Car(randomUUID(), "Volvo", "xc60", "none", 4,
            empty(), new String[]{"ac"}, "{}", instant(), Optional.of(instant()));
        api.post("/cars", volvo).body();

        final var response = api.get("/cars/%s".formatted(volvo.id()));

        assertThat(parser.parse(response.body())).isEqualTo(volvo);
    }
}
