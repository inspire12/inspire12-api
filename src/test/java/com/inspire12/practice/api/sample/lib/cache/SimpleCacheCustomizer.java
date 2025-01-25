package com.inspire12.practice.api.sample.lib.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import java.util.Arrays;

//@Component
public class SimpleCacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(Arrays.asList("users", "transactions"));
    }
}
