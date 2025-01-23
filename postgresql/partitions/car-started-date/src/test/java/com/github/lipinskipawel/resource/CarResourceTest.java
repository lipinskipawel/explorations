package com.github.lipinskipawel.resource;

import com.github.lipinskipawel.IntegrationSpec;
import com.github.lipinskipawel.db.Car;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.lipinskipawel.db.CarState.ASSEMBLED;
import static java.util.UUID.randomUUID;

final class CarResourceTest
    extends IntegrationSpec
    implements WithAssertions {

    @Test
    void save_car() throws IOException, InterruptedException {
        final var volvo = new Car(randomUUID(), "volvo", "xc60", ASSEMBLED, instant());
        api.post("/cars", volvo).body();

        final var response = api.get("/cars/%s".formatted(volvo.id()));

        assertThat(parser.parse(response.body())).isEqualTo(volvo);
    }
}
