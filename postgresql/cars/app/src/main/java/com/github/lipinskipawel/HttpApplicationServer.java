package com.github.lipinskipawel;

import io.javalin.Javalin;
import com.github.lipinskipawel.db.CarRepository;

import static io.javalin.Javalin.create;
import static java.util.Objects.requireNonNull;

public final class HttpApplicationServer {
    private final Javalin javalin;

    private HttpApplicationServer(Javalin javalin) {
        this.javalin = requireNonNull(javalin);
    }

    public static HttpApplicationServer httpServer(CarRepository carRepository) {
        final var javalinApplication = create(conf -> conf.http.defaultContentType = "application/json");

        Runtime.getRuntime().addShutdownHook(new Thread(javalinApplication::close));
        return new HttpApplicationServer(javalinApplication);
    }

    public void start(int port) {
        javalin.start(port);
    }
}
