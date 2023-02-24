package com.inspire12.practice.api.sample.lib.cache;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface CacheService<K, V> {
    Map<K, V> get(String cacheKey);

    Optional<V> getOpt(String cacheKey, K key);

    boolean lock(String cacheKey, K key, long leaseTime, TimeUnit timeUnit);

    void unlock(String cacheKey, K key);

    void set(String cacheKey, K key, V value);

    void set(String cacheKey, K key, V value, long ttl, TimeUnit timeUnit);

    void merge(String cacheKey, K key, V value);

    void setAsync(String cacheKey, K key, V value);

    void setAsync(String cacheKey, K key, V value, long ttl, TimeUnit timeUnit);


    void replace(String cacheKey, K key, V value);

    void putAsync(String cacheKey, K key, V value);

    void putAsync(String cacheKey, K key, V value, Consumer<V> consumer);

    void remove(String cacheKey, K key);

    void evict(String cacheKey, K key);

    boolean containsKey(String cacheKey, K key);

    void putAsync(String cacheKey, K key, V value, long ttl, TimeUnit timeUnit, Consumer<V> consumer);

    void clear(String cacheKey);

    Map<K, V> getCachedMap(String cacheKey);

    Set<K> getCachedMapKeySet(String cacheKey, Predicate<K> predicate);

    V updateSynchronized(String cacheKey, K key, V value);

    V updateSynchronized(String cacheKey, K key, Function<V, V> updateFunc, Supplier<V> insertFunc);

    V updateSynchronizedWithCondition(String cacheKey, K key, Function<V, V> updateFunc, Supplier<V> insertFunc, Predicate<V> predicate, Supplier<V> failToReturn);

    void removeSynchronized(String cacheKey, K key, Function<V, V> removeFunc);
}
