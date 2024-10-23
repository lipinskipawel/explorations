package com.github.lipinskipawel.db;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public final class Car {
    private final UUID id;
    private final String brand;
    private final String model;
    private final CarState state;
    private final Optional<Instant> assembledData;

    public Car(
        UUID id,
        String brand,
        String model,
        CarState state,
        Optional<Instant> assembledData
    ) {
        this.id = requireNonNull(id);
        this.brand = requireNonNull(brand);
        this.model = requireNonNull(model);
        this.state = requireNonNull(state);
        this.assembledData = requireNonNull(assembledData);
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

    public Optional<Instant> assembledData() {
        return assembledData;
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
            && Objects.equals(assembledData, car.assembledData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, state, assembledData);
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", state='" + state + '\'' +
            ", assembledData=" + assembledData +
            '}';
    }
}
