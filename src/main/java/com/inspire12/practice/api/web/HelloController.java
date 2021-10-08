package com.inspire12.practice.api.web;

import com.inspire12.practice.api.lib.measure.TimeChecker;
import com.inspire12.practice.api.web.dto.HelloResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    @TimeChecker
    @Cacheable("hello")
    public String hello() throws InterruptedException {
        Thread.sleep(10000);
        return "hello";
    }

    @Autowired
    CacheManager cacheManager;

    @GetMapping("/hello/cache")
    @TimeChecker
    public String hello2() {
        Cache cache = cacheManager.getCache("hello");
        return "hello";
    }

    @GetMapping("/api/v1/hello/dto")
    @TimeChecker
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                           @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }
}
