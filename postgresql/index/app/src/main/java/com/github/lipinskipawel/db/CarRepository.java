package com.github.lipinskipawel.db;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.UUID;

import static com.github.lipinskipawel.jooq.tables.Cars.CARS;

public final class CarRepository extends AbstractRepository {

    public CarRepository(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Car car) {
        final var record = CarMapper.toRecord(car);
        return db.executeInsert(record);
    }

    public Optional<Car> find(UUID id) {
        return db.selectFrom(CARS)
            .where(CARS.ID.eq(id))
            .fetch()
            .map(CarMapper::fromRecord)
            .stream()
            .findAny();
    }

    public void truncateCars() {
        db.truncate(CARS).execute();
    }
}
