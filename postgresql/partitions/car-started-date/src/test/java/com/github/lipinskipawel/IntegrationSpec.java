package com.github.lipinskipawel;

import com.github.lipinskipawel.db.CarRepository;
import com.github.lipinskipawel.json.Parser;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.Instant;

import static com.github.lipinskipawel.HttpApplicationServer.httpServer;
import static java.time.Clock.systemUTC;
import static java.time.Clock.tick;
import static java.time.temporal.ChronoUnit.MICROS;
import static org.testcontainers.utility.DockerImageName.parse;

public abstract class IntegrationSpec {

    public static final CarApi api;
    public static final Parser parser;
    public static final DataSource dataSource;
    private static final int PORT = 8099;

    static {
        parser = new Parser();
        api = new CarApi(PORT, parser);
        dataSource = dataSource(dbInstance());
        final var database = new Flyway(dataSource);
        database.migrate();
        final var carRepository = new CarRepository(dataSource);

        final var app = httpServer(carRepository, parser);
        app.start(PORT);
    }

    public static Instant instant() {
        return tick(systemUTC(), MICROS.getDuration()).instant();
    }

    private static DataSource dataSource(JdbcDatabaseContainer<?> dbInstance) {
        final var dataSource = new PGSimpleDataSource();

        dataSource.setUrl(dbInstance.getJdbcUrl());
        dataSource.setUser(dbInstance.getUsername());
        dataSource.setPassword(dbInstance.getPassword());

        return dataSource;
    }

    private static PostgreSQLContainer<?> dbInstance() {
        try {
            final var image = parse("ghcr.io/lipinskipawel/postgresql16-pg_partman5:1")
                .asCompatibleSubstituteFor("postgres");
            final var postgresContainer = new PostgreSQLContainer<>(image)
                .withDatabaseName("cars")
                .withUsername("car_user")
                .withPassword("car_password");
            postgresContainer.start();
            postgresContainer.execInContainer("psql", "-U", "car_user", "-p", "5432", "-d", "cars",
                "-c", """
                    CREATE SCHEMA IF NOT EXISTS partman;
                    CREATE EXTENSION pg_partman SCHEMA partman;
                    CREATE ROLE partman_user;
                    GRANT ALL ON ALL TABLES IN SCHEMA partman TO partman_user;
                    GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA partman TO partman_user;
                    GRANT USAGE ON SCHEMA partman TO partman_user;
                    GRANT ALL ON SCHEMA partman TO partman_user;
                    """);
            return postgresContainer;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
