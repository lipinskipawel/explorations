package com.github.lipinskipawel.resource;

import com.github.lipinskipawel.db.CarRepository;
import com.github.lipinskipawel.json.Parser;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Handler;

import java.util.UUID;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static java.util.Objects.requireNonNull;

public final class CarResource implements EndpointGroup {

    private final CarRepository carRepository;
    private final Parser parser;
    private final Routes routes = new Routes();

    public CarResource(CarRepository carRepository, Parser parser) {
        this.carRepository = requireNonNull(carRepository);
        this.parser = requireNonNull(parser);
    }

    @Override
    public void addEndpoints() {
        get("/cars/{id}", routes.getCar);
        post("/cars", routes.createCar);
    }

    private final class Routes {
        private final Handler getCar = ctx -> {
            final var id = UUID.fromString(ctx.pathParam("id"));
            final var car = carRepository.find(id).map(parser::parse);
            if (car.isPresent()) {
                ctx.result(car.get());
                return;
            }
            ctx.res().setStatus(404);
        };

        private final Handler createCar = ctx -> {
            final var car = parser.parse(ctx.body());
            carRepository.save(car);
        };
    }
}
