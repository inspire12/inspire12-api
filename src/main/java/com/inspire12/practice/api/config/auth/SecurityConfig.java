package com.inspire12.practice.api.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig { // extends WebSecurityConfigurerAdapter {
//    private final CustomOAuth2UserService customOAuth2UserService;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .headers().frameOptions().disable()
//            .and()
//                .authorizeRequests()
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**")
//                    .permitAll()
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated()
//            .and()
//                .logout()
//                    .logoutSuccessUrl("/")
//            .and()
//                .oauth2Login()
//                    .userInfoEndpoint()
//                        .userService(customOAuth2UserService);
//    }
}

