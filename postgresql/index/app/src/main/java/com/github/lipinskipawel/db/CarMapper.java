package com.github.lipinskipawel.db;

import com.github.lipinskipawel.jooq.tables.records.CarsRecord;

import static java.util.Optional.ofNullable;

final class CarMapper {

    static CarsRecord toRecord(Car car) {
        return new CarsRecord(
            car.id(),
            car.brand(),
            car.model(),
            car.submodel(),
            car.wheels(),
            car.currentOwner().orElse(null),
            car.features(),
            car.details(),
            car.createdDate(),
            car.assembledData().orElse(null)
        );
    }

    static Car fromRecord(CarsRecord carsRecord) {
        return new Car(
            carsRecord.getId(),
            carsRecord.getBrand(),
            carsRecord.getModel(),
            carsRecord.getSubmodel(),
            carsRecord.getWheels(),
            ofNullable(carsRecord.getCurrentOwner()),
            carsRecord.getFeatures(),
            carsRecord.getDetails(),
            carsRecord.getCreatedDate(),
            ofNullable(carsRecord.getAssembledDate())
        );
    }
}
