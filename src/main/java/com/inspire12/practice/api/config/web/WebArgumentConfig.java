package com.inspire12.practice.api.config.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@RequiredArgsConstructor
@Configuration
public class WebArgumentConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/h2-console/**")
            .allowedOrigins("http://localhost:8080")
            .allowedMethods("*");

        registry.addMapping("/favicon.ico")
            .allowedOrigins("http://localhost:8080")
            .allowedMethods("*");

        registry.addMapping("/api/v1/**") // CORS 적용 경로
                .allowedOrigins("http://localhost:8080") // 허용할 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
