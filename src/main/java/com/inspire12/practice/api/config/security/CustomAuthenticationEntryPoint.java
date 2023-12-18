//package com.inspire12.practice.api.config.security;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.io.IOException;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    private final HandlerExceptionResolver handlerExceptionResolver;
//
//    @Override
//    public void commence(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException authException) throws IOException {
//        log.error("{}", request.getRequestURI(), authException);
//        handlerExceptionResolver.resolveException(request, response, null, authException);
//    }
//}
