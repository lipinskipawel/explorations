package com.github.lipinskipawel;

import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

import static com.github.lipinskipawel.HttpApplicationServer.httpServer;

public abstract class IntegrationSpec {

    public static final DataSource dataSource;
    public static final AdvisoryLock advisoryLock;
    private static final int PORT = 8099;

    static {
        dataSource = dataSource(dbInstance());

        advisoryLock = new AdvisoryLock(dataSource);
        final var app = httpServer();
        app.start(PORT);
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
