package com.inspire12.practice.api.presentation.controller;

import com.inspire12.practice.api.config.measure.TimeChecker;
import com.inspire12.practice.api.presentation.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CacheController {
    private final CacheManager cacheManager;

    @GetMapping("/cache")
    @TimeChecker
    @Cacheable("hello")
    public ResponseEntity<String> hello() throws InterruptedException {
        Thread.sleep(10000);
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/cache/manager")
    @TimeChecker
    public ResponseEntity<String> hello2() {
        Cache cache = cacheManager.getCache("hello");
        if (cache != null) {
            return ResponseEntity.ok(cache.getName());
        }
        return ResponseEntity.ok("failed");
    }
}
