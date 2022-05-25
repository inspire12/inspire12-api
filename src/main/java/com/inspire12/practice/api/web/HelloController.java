package com.inspire12.practice.api.web;

import com.inspire12.practice.api.config.datasource.ApiDataSource;
import com.inspire12.practice.api.config.datasource.CommunityPostDataSource;
import com.inspire12.practice.api.lib.measure.TimeChecker;
import com.inspire12.practice.api.web.dto.HelloResponseDto;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;


@RestController
public class HelloController {

    @GetMapping("/hello")
    @TimeChecker
    public String hello() {
        return "hello";
    }

    @GetMapping("/api/v1/hello/dto")
    @TimeChecker
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                           @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }
}
