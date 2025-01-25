package com.inspire12.practice.api.module.test.presentation.controller.sample;

import com.inspire12.practice.api.config.measure.TimeChecker;
import com.inspire12.practice.api.config.model.CommonResponse;
import lombok.RequiredArgsConstructor;
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
    public CommonResponse<String> hello() throws InterruptedException {
        Thread.sleep(10000);
        return new CommonResponse<>("hello");
    }

    @GetMapping("/cache/manager")
    @TimeChecker
    public CommonResponse<String> hello2() {
        Cache cache = cacheManager.getCache("hello");
        if (cache != null) {
            return new CommonResponse<>(cache.getName());
        }
        return new CommonResponse<>("failed");
    }
}
