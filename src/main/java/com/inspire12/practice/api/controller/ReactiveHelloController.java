package com.inspire12.practice.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive")
public class ReactiveHelloController {
    @GetMapping("/{id}")
    public Mono<String> getReactive(@PathVariable String id) {
        return Mono.create(stringMonoSink -> stringMonoSink.success("Hello " + id));
    }
}
