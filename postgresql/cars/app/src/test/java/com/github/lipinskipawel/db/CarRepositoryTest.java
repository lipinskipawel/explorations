package com.github.lipinskipawel.db;

import com.github.lipinskipawel.IntegrationSpec;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static com.github.lipinskipawel.db.CarState.ASSEMBLED;
import static java.time.Clock.systemUTC;
import static java.time.Clock.tick;
import static java.time.temporal.ChronoUnit.MICROS;
import static java.util.UUID.randomUUID;

final class CarRepositoryTest
    extends IntegrationSpec
    implements WithAssertions {

    private final CarRepository carRepository = new CarRepository(dataSource);

    @Test
    void add_car_to_database() {
        final var volvo = xc60Volvo();
        System.out.println(volvo);
        carRepository.save(volvo);

        final var fromRepo = carRepository.find(volvo.id());

        assertThat(fromRepo).satisfies(it -> {
            assertThat(it).isPresent();
            assertThat(it.get()).isEqualTo(volvo);
        });
    }

    private Car xc60Volvo() {
        return new Car(randomUUID(), "Volvo", "xc60", ASSEMBLED, Optional.of(instant()));
    }

    public Instant instant() {
        return tick(systemUTC(), MICROS.getDuration()).instant();
    }
}
