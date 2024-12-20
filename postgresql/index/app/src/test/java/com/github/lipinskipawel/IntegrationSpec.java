package com.github.lipinskipawel;

import com.github.lipinskipawel.db.CarRepository;
import com.github.lipinskipawel.json.Parser;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.time.Instant;

import static com.github.lipinskipawel.HttpApplicationServer.httpServer;
import static java.time.Clock.systemUTC;
import static java.time.Clock.tick;
import static java.time.temporal.ChronoUnit.MICROS;

public abstract class IntegrationSpec {

    public static final CarApi api;
    public static final Parser parser;
    public static final DataSource dataSource;
    public static final CarRepository carRepository;
    private static final int PORT = 8099;

    static {
        parser = new Parser();
        api = new CarApi(PORT, parser);
        dataSource = dataSource(dbInstance());
        final var database = new Flyway(dataSource);
        database.migrate();

        carRepository = new CarRepository(dataSource);
        final var app = httpServer(carRepository, parser);
        app.start(PORT);
    }

    public static Instant instant() {
        return tick(systemUTC(), MICROS.getDuration()).instant();
    }

    public static void truncateCars() {
        carRepository.truncateCars();
    }

    private static DataSource dataSource(JdbcDatabaseContainer<?> dbInstance) {
        final var dataSource = new PGSimpleDataSource();

        dataSource.setUrl(dbInstance.getJdbcUrl());
        dataSource.setUser(dbInstance.getUsername());
        dataSource.setPassword(dbInstance.getPassword());

        return dataSource;
    }

    private static PostgreSQLContainer<?> dbInstance() {
        final var postgresContainer = new PostgreSQLContainer<>("postgres:16.4")
            .withDatabaseName("cars")
            .withUsername("car_user")
            .withPassword("car_password");
        postgresContainer.start();
        return postgresContainer;
    }
}
