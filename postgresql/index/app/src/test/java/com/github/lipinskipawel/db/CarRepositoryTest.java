package com.github.lipinskipawel.db;

import com.github.lipinskipawel.IntegrationSpec;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.UUID.randomUUID;

final class CarRepositoryTest
    extends IntegrationSpec
    implements WithAssertions {

    private final CarRepository carRepository = new CarRepository(dataSource);

    @AfterAll
    static void afterAll() {
        truncateCars();
    }

    @Test
    void add_car_to_database() {
        final var volvo = xc60Volvo();
        carRepository.save(volvo);

        final var fromRepo = carRepository.find(volvo.id());

        assertThat(fromRepo).satisfies(it -> {
            assertThat(it).isPresent();
            assertThat(it.get()).isEqualTo(volvo);
        });
    }

    private Car xc60Volvo() {
        return new Car(randomUUID(), "Volvo", "xc60", "none", 4,
            empty(), new String[]{"ac"}, "{}", instant(), Optional.of(instant()));
    }
}