package com.github.lipinskipawel.db;

import com.github.lipinskipawel.jooq.tables.records.CarsStartedDateRecord;

final class CarMapper {

    static CarsStartedDateRecord toRecord(Car car) {
        return new CarsStartedDateRecord(
            car.id(),
            car.brand(),
            car.model(),
            car.state().name(),
            car.startedDate()
        );
    }

    static Car fromRecord(CarsStartedDateRecord carsRecord) {
        return new Car(
            carsRecord.getId(),
            carsRecord.getBrand(),
            carsRecord.getModel(),
            CarState.valueOf(carsRecord.getState()),
            carsRecord.getStartedDate()
        );
    }
}
