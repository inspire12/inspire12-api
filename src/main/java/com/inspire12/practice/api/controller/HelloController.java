package com.inspire12.practice.api.controller;

import com.inspire12.practice.api.config.logging.performance.TimeChecker;
import com.inspire12.practice.api.model.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/vi/hello")
@RestController
public class HelloController {

    @TimeChecker
    @GetMapping("")
    public String hello() {
        return "hello";
    }

    @TimeChecker
    @GetMapping("/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
