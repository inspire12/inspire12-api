package com.inspire12.practice.api.module.test.presentation.controller.api;

import com.inspire12.practice.api.config.model.CommonResponse;
import com.inspire12.practice.api.util.Inspire12MathUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/utils")
public class ApiController {
    @GetMapping("/elo/xG")
    public CommonResponse<Double> getXg(@RequestParam double me,
        @RequestParam double op) {
        return CommonResponse.create(Inspire12MathUtils.getEloExpectWinRatio(op, me));
    }

    @GetMapping("/test")
    public CommonResponse<Long> test(@RequestParam("a") long a,
        @RequestParam("b") long b) {
        long x = a ^ b;
        return CommonResponse.create(x);
    }
}
