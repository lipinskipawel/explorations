package com.github.lipinskipawel.db;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.UUID;

import static com.github.lipinskipawel.jooq.Tables.CARS_STARTED_DATE;

public final class CarRepository extends AbstractRepository {

    public CarRepository(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Car car) {
        final var record = CarMapper.toRecord(car);
        return db.executeInsert(record);
    }

    public Optional<Car> find(UUID id) {
        return db.selectFrom(CARS_STARTED_DATE)
            .where(CARS_STARTED_DATE.ID.eq(id))
            .fetch()
            .map(CarMapper::fromRecord)
            .stream()
            .findAny();
    }
}
