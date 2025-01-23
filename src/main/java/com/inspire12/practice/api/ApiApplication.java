package com.inspire12.practice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.inspire12.practice.api")
public class ApiApplication {

//    @TimeChecker // 부팅
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
