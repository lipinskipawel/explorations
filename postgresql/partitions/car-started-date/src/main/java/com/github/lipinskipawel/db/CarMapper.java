package com.github.lipinskipawel.db;

import com.github.lipinskipawel.jooq.tables.records.CarsStartedDateRecord;

import static java.util.Optional.ofNullable;

final class CarMapper {

    static CarsStartedDateRecord toRecord(Car car) {
        return new CarsStartedDateRecord(
            car.id(),
            car.brand(),
            car.model(),
            car.state().name(),
            car.startedDate().orElse(null)
        );
    }

    static Car fromRecord(CarsStartedDateRecord carsRecord) {
        return new Car(
            carsRecord.getId(),
            carsRecord.getBrand(),
            carsRecord.getModel(),
            CarState.valueOf(carsRecord.getState()),
            ofNullable(carsRecord.getStartedDate())
        );
    }
}
