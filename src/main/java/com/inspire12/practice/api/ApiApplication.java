package com.inspire12.practice.api;

import com.inspire12.practice.api.config.measure.TimeChecker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiApplication {

    @TimeChecker // 부팅
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
