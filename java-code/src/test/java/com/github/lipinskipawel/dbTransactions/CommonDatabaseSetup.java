package com.github.lipinskipawel.dbTransactions;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

public abstract class CommonDatabaseSetup {

    protected static DataSource dataSource;

    static {
        final var postgres = startPostgresContainer();
        dataSource = dataSource(postgres);
        migrateDatabase(dataSource);
    }

    private static PostgreSQLContainer<?> startPostgresContainer() {
        final var postgres = new PostgreSQLContainer<>("postgres:16.4")
            .withDatabaseName("test")
            .withUsername("user")
            .withPassword("pass");
        postgres.start();
        return postgres;
    }

    private static DataSource dataSource(PostgreSQLContainer<?> container) {
        final var dataSource = new PGSimpleDataSource();

        dataSource.setUrl(container.getJdbcUrl());
        dataSource.setUser(container.getUsername());
        dataSource.setPassword(container.getPassword());

        return dataSource;
    }

    private static void migrateDatabase(DataSource dataSource) {
        Flyway.configure()
            .dataSource(dataSource)
            .load().migrate();
    }
}
