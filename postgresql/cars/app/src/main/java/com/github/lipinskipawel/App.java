package com.github.lipinskipawel;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.github.lipinskipawel.db.CarRepository;

import javax.sql.DataSource;

public class App {

    public static void main(String[] args) {
        final var dataSource = dataSource();
        final var database = new Flyway(dataSource);
        database.migrate();
        final var carRepository = new CarRepository(dataSource);

        final var app = HttpApplicationServer.httpServer(carRepository);
        app.start(8090);
    }

    private static DataSource dataSource() {
        final var config = new HikariConfig();

        config.setJdbcUrl("jdbc:postgresql://localhost:5432/cars");
        config.setUsername("car_user");
        config.setPassword("car_password");
        config.addDataSourceProperty("minimumIdle", "5");
        config.addDataSourceProperty("maximumPoolSize", "25");

        return new HikariDataSource(config);
    }
}
