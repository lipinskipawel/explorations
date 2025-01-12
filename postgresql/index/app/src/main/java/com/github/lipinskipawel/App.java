package com.github.lipinskipawel;

import com.github.lipinskipawel.db.CarRepository;
import com.github.lipinskipawel.json.Parser;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import static com.github.lipinskipawel.HttpApplicationServer.httpServer;

public class App {

    public static void main(String[] args) {
        final var dataSource = dataSource();
        final var database = new Flyway(dataSource);
        database.migrate();
        final var parser = new Parser();
        final var carRepository = new CarRepository(dataSource);

        final var app = httpServer(carRepository, parser);
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
