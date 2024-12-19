package com.github.lipinskipawel.db;

import com.github.lipinskipawel.jooq.tables.records.CarsRecord;

import static java.util.Optional.ofNullable;

final class CarMapper {

    static CarsRecord toRecord(Car car) {
        return new CarsRecord(
            car.id(),
            car.brand(),
            car.model(),
            car.state().name(),
            car.assembledData().orElse(null)
        );
    }

    static Car fromRecord(CarsRecord carsRecord) {
        return new Car(
            carsRecord.getId(),
            carsRecord.getBrand(),
            carsRecord.getModel(),
            CarState.valueOf(carsRecord.getState()),
            ofNullable(carsRecord.getAssembledDate())
        );
    }
}
