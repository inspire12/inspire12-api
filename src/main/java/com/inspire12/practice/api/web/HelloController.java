package com.inspire12.practice.api.web;

import com.inspire12.practice.api.lib.measure.TimeChecker;
import com.inspire12.practice.api.web.dto.HelloResponseDto;
import com.inspire12.practice.api.web.dto.Product;
import com.inspire12.practice.api.web.service.MockoonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final MockoonService mockoonService;

    @GetMapping("/hello")
    @TimeChecker
    public List<Product> hello(@RequestParam("q") String query) {
        return mockoonService.getProducts(query);
    }

    @GetMapping("/api/v1/hello/dto")
    @TimeChecker
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                           @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }
}
