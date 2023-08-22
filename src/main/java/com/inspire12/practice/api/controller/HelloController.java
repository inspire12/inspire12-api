package com.inspire12.practice.api.controller;

import com.inspire12.practice.api.config.logging.performance.TimeChecker;
import com.inspire12.practice.api.model.dto.HelloResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RequestMapping("/api/v1/hello")
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

    @GetMapping("/clientIp")
    public String helloDto(RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) {
        log.info("clientIp {} {}", request.getRemoteAddr(), request.getRemoteUser());
        return request.getRemoteAddr();
    }
}
