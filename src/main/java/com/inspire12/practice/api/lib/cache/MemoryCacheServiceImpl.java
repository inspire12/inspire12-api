package com.inspire12.practice.api.lib.cache;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
public class MemoryCacheServiceImpl implements CacheService<String, CacheObject>{

    Map<String, CacheObject> map = new ConcurrentHashMap<>();

    @Override
    public Map<String, CacheObject> get(String cacheKey) {
        return map;
    }

    @Override
    public Optional<CacheObject> getOpt(String cacheKey, String key) {
        return Optional.empty();
    }

    @Override
    public boolean lock(String cacheKey, String key, long leaseTime, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public void unlock(String cacheKey, String key) {

    }

    @Override
    public void set(String cacheKey, String key, CacheObject value) {

    }

    @Override
    public void set(String cacheKey, String key, CacheObject value, long ttl, TimeUnit timeUnit) {

    }

    @Override
    public void merge(String cacheKey, String key, CacheObject value) {

    }

    @Override
    public void setAsync(String cacheKey, String key, CacheObject value) {

    }

    @Override
    public void setAsync(String cacheKey, String key, CacheObject value, long ttl, TimeUnit timeUnit) {

    }

    @Override
    public void replace(String cacheKey, String key, CacheObject value) {

    }

    @Override
    public void putAsync(String cacheKey, String key, CacheObject value) {

    }

    @Override
    public void putAsync(String cacheKey, String key, CacheObject value, Consumer<CacheObject> consumer) {

    }

    @Override
    public void remove(String cacheKey, String key) {

    }

    @Override
    public void evict(String cacheKey, String key) {

    }

    @Override
    public boolean containsKey(String cacheKey, String key) {
        return false;
    }

    @Override
    public void putAsync(String cacheKey, String key, CacheObject value, long ttl, TimeUnit timeUnit, Consumer<CacheObject> consumer) {

    }

    @Override
    public void clear(String cacheKey) {

    }

    @Override
    public Map<String, CacheObject> getCachedMap(String cacheKey) {
        return null;
    }

    @Override
    public Set<String> getCachedMapKeySet(String cacheKey, Predicate<String> predicate) {
        return null;
    }

    @Override
    public CacheObject updateSynchronized(String cacheKey, String key, CacheObject value) {
        return null;
    }

    @Override
    public CacheObject updateSynchronized(String cacheKey, String key, Function<CacheObject, CacheObject> updateFunc, Supplier<CacheObject> insertFunc) {
        return null;
    }

    @Override
    public CacheObject updateSynchronizedWithCondition(String cacheKey, String key, Function<CacheObject, CacheObject> updateFunc, Supplier<CacheObject> insertFunc, Predicate<CacheObject> predicate, Supplier<CacheObject> failToReturn) {
        return null;
    }

    @Override
    public void removeSynchronized(String cacheKey, String key, Function<CacheObject, CacheObject> removeFunc) {

    }
//    CacheManager cacheManager

}
