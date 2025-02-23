package com.github.lipinskipawel;

import com.github.lipinskipawel.db.CarRepository;
import com.github.lipinskipawel.json.Parser;
import com.github.lipinskipawel.resource.CarResource;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.plugin.bundled.CorsPluginConfig;

import java.util.List;

import static io.javalin.Javalin.create;
import static java.util.Objects.requireNonNull;

public final class HttpApplicationServer {
    private final Javalin javalin;

    private HttpApplicationServer(Javalin javalin) {
        this.javalin = requireNonNull(javalin);
    }

    public static HttpApplicationServer httpServer(CarRepository carRepository, Parser parser) {
        final var javalinApplication = create(conf -> {
            conf.http.defaultContentType = "application/json";
            conf.plugins.enableCors(cors -> cors.add(CorsPluginConfig::anyHost));
        });

        final var routes = createRoutes(carRepository, parser);
        routes.forEach(javalinApplication::routes);

        Runtime.getRuntime().addShutdownHook(new Thread(javalinApplication::close));
        return new HttpApplicationServer(javalinApplication);
    }

    public void start(int port) {
        javalin.start(port);
    }

    private static List<EndpointGroup> createRoutes(CarRepository carRepository, Parser parser) {
        return List.of(
            new CarResource(carRepository, parser)
        );
    }
}
