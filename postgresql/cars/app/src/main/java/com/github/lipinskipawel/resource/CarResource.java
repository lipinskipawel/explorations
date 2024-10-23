package com.github.lipinskipawel.resource;

import com.github.lipinskipawel.db.CarRepository;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Handler;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static java.util.Objects.requireNonNull;

public final class CarResource implements EndpointGroup {
    private final CarRepository carRepository;
    private final Routes routes = new Routes();

    public CarResource(CarRepository carRepository) {
        this.carRepository = requireNonNull(carRepository);
    }

    @Override
    public void addEndpoints() {
        get("/cars", routes.getCar);
        post("/cars", routes.createCar);
    }

    private final class Routes {
        private final Handler getCar = ctx -> {
        };

        private final Handler createCar = ctx -> {
        };
    }
}
