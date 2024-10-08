package com.github.lipinskipawel.cache.v1;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

final class OverrideCache<K, V> {

    private AtomicReference<Map<K, V>> cache;

    public V get(K key) {
        return cache.get().get(key);
    }

    public Optional<V> find(K key) {
        return Optional.ofNullable(cache.get().get(key));
    }

    public void updateCache(Map<K, V> newCacheValues) {
        this.cache.set(newCacheValues);
    }
}
