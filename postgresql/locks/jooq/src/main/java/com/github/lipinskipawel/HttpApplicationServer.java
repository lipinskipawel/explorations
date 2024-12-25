package com.github.lipinskipawel;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

import static io.javalin.Javalin.create;
import static java.util.Objects.requireNonNull;

public final class HttpApplicationServer {
    private final Javalin javalin;

    private HttpApplicationServer(Javalin javalin) {
        this.javalin = requireNonNull(javalin);
    }

    public static HttpApplicationServer httpServer() {
        final var javalinApplication = create(conf -> {
            conf.http.defaultContentType = "application/json";
            conf.plugins.enableCors(cors -> cors.add(CorsPluginConfig::anyHost));
        });

        Runtime.getRuntime().addShutdownHook(new Thread(javalinApplication::close));
        return new HttpApplicationServer(javalinApplication);
    }

    public void start(int port) {
        javalin.start(port);
    }
}
