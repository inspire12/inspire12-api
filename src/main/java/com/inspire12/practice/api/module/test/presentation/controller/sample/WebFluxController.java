package com.inspire12.practice.api.module.test.presentation.controller.sample;

import com.inspire12.practice.api.module.posts.presentation.response.PostResponse;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flux")
public class WebFluxController {

    private final ServerProperties serverProperties;


}
