package com.inspire12.practice.api.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/play")
@RestController
public class PlayController {
    @GetMapping
    public <T> ResponseEntity<T> play() {

        return ResponseEntity.ok().build();
    }
}
