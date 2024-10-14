package com.github.lipinskipawel.cache.v2;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

final class OverrideCache<K, V> {

    private final AtomicReference<Map<K, V>> cache;

    public OverrideCache(Callable<Map<K, V>> callable) {
        cache = new AtomicReference<>(Map.of());
        newSingleThreadScheduledExecutor()
            .scheduleWithFixedDelay(() -> {
                try {
                    cache.set(callable.call());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, 60, 60, SECONDS);
    }

    public V get(K key) {
        return cache.get().get(key);
    }

    public Optional<V> find(K key) {
        return Optional.ofNullable(cache.get().get(key));
    }
}
