package com.inspire12.practice.api.presentation.controller;

import com.inspire12.practice.api.config.measure.TimeChecker;
import com.inspire12.practice.api.domain.mock.HelloResponseDto;
import com.inspire12.practice.api.presentation.model.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    @TimeChecker
    public CommonResponse<String> hello() {
        return new CommonResponse<>("hello");
    }

    @GetMapping("/api/v1/hello/dto")
    @TimeChecker
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }
}