package com.github.lipinskipawel.cache.v1;

import java.util.Map;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

final class Caller {
    private final OverrideCache<Integer, String> cache;

    Caller(OverrideCache<Integer, String> cache) {
        this.cache = cache;
        startCache();
    }

    public void startCache() {
        final Runnable populateCache = () -> {
            final var result = Map.of(1, "1");
            cache.updateCache(result);
        };
        Executors.newScheduledThreadPool(1)
            .scheduleAtFixedRate(populateCache, 30, 60, SECONDS);
    }
}
