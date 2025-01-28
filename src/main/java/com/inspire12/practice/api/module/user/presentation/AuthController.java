package com.inspire12.practice.api.module.user.presentation;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/success")
    public String success(OAuth2User user) {
        // OAuth2 사용자 정보를 가져옵니다.
        return "Hello, " + user.getAttribute("name");
    }
}
