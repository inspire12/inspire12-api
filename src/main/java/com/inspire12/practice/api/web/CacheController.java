package com.inspire12.practice.api.web;

import com.inspire12.practice.api.lib.measure.TimeChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CacheController {

    private final CacheManager cacheManager;

    @GetMapping("/cache")
    @TimeChecker
    @Cacheable("hello")
    public String hello() throws InterruptedException {
        Thread.sleep(10000);
        return "hello";
    }

    @GetMapping("/cache/manager")
    @TimeChecker
    public String hello2() {
        Cache cache = cacheManager.getCache("hello");
        if (cache != null) {
            return cache.getName();
        }
        return "failed";
    }
}
