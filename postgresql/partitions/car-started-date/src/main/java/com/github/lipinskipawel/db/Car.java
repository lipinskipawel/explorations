package com.github.lipinskipawel.db;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public final class Car {
    private final UUID id;
    private final String brand;
    private final String model;
    private final CarState state;
    private final Instant startedDate;

    public Car(
        UUID id,
        String brand,
        String model,
        CarState state,
        Instant startedDate
    ) {
        this.id = requireNonNull(id);
        this.brand = requireNonNull(brand);
        this.model = requireNonNull(model);
        this.state = requireNonNull(state);
        this.startedDate = requireNonNull(startedDate);
    }

    public UUID id() {
        return id;
    }

    public String brand() {
        return brand;
    }

    public String model() {
        return model;
    }

    public CarState state() {
        return state;
    }

    public Instant startedDate() {
        return startedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id)
            && Objects.equals(brand, car.brand)
            && Objects.equals(model, car.model)
            && Objects.equals(state, car.state)
            && Objects.equals(startedDate, car.startedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, state, startedDate);
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", state='" + state + '\'' +
            ", startedDate=" + startedDate +
            '}';
    }
}
