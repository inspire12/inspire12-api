package com.inspire12.practice.api.controller.lab;

import com.inspire12.practice.api.config.measure.TimeChecker;
import com.inspire12.practice.api.service.domain.mock.HelloResponseDto;
import com.inspire12.model.CommonResponse;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    @GetMapping("/test")
    @TimeChecker
    public String test() {
        List<Tag> tags = List.of(
                Tag.of("my_label_a", "A"),
                Tag.of("my_label_b", "B")
        );
        Metrics.counter("my.demo.count.total", tags).increment();
        return "test";
    }
}
