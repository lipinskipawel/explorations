package com.github.lipinskipawel.db;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public final class Car {
    private final UUID id;
    private final String brand;
    private final String model;
    private final String submodel;
    private final int wheels;
    private final Optional<UUID> currentOwner;
    private final String[] features;
    private final String details;
    private final Instant createdDate;
    private final Optional<Instant> assembledDate;

    public Car(
        UUID id,
        String brand,
        String model,
        String submodel,
        int wheels,
        Optional<UUID> currentOwner,
        String[] features,
        String details,
        Instant createdDate,
        Optional<Instant> assembledDate
    ) {
        this.id = requireNonNull(id);
        this.brand = requireNonNull(brand);
        this.model = requireNonNull(model);
        this.submodel = requireNonNull(submodel);
        this.wheels = wheels;
        this.currentOwner = currentOwner;
        this.features = requireNonNull(features);
        this.details = requireNonNull(details);
        this.createdDate = requireNonNull(createdDate);
        this.assembledDate = assembledDate;
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

    public String submodel() {
        return submodel;
    }

    public int wheels() {
        return wheels;
    }

    public Optional<UUID> currentOwner() {
        return currentOwner;
    }

    public String[] features() {
        return features;
    }

    public String details() {
        return details;
    }

    public Instant createdDate() {
        return createdDate;
    }

    public Optional<Instant> assembledData() {
        return assembledDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return wheels == car.wheels && Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(submodel, car.submodel) && Objects.equals(currentOwner, car.currentOwner) && Objects.deepEquals(features, car.features) && Objects.equals(details, car.details) && Objects.equals(createdDate, car.createdDate) && Objects.equals(assembledDate, car.assembledDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, submodel, wheels, currentOwner, Arrays.hashCode(features), details, createdDate, assembledDate);
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", submodel='" + submodel + '\'' +
            ", wheels=" + wheels +
            ", currentOwner=" + currentOwner +
            ", features=" + Arrays.toString(features) +
            ", details='" + details + '\'' +
            ", createdDate=" + createdDate +
            ", assembledDate=" + assembledDate +
            '}';
    }
}
