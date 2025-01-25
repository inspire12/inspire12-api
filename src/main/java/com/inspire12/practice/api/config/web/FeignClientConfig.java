package com.inspire12.practice.api.config.web;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.concurrent.TimeUnit;

@Configuration
public class FeignClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignError();
    }

    @Bean
    Retryer.Default retryer() {
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(3L), 3);
    }


}
