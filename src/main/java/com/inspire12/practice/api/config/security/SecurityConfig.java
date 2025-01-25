package com.inspire12.practice.api.config.security;

import com.inspire12.practice.api.module.user.infrastructure.adapter.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//            .password("user")
//            .roles("USER")
//            .build();
//        return new MapReactiveUserDetailsService(user);
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring()
//            .requestMatchers("/", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**"); // 정적 리소스 제외
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("*")); // 모든 오리진 허용
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                return config;
            }))
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/api/v1/**", "/h2-console/**", "/favicon.ico").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
//        return
//            http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth ->
//                    auth.anyRequest().authenticated()
//                )
//                .sessionManagement(session -> {
//                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                })
//
//                .formLogin(AbstractHttpConfigurer::disable)
//                .exceptionHandling(exceptionHandler -> {
//                        exceptionHandler
//                            .authenticationEntryPoint(customAuthenticationEntryPoint)
//                            .accessDeniedHandler(customAccessDeniedHandler);
//                    }
//                )
//                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/"))
//
//                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login(oauth -> {
//                    oauth.userInfoEndpoint(userInfoEndpointConfig ->
//                        userInfoEndpointConfig.userService(customOAuth2UserService));
//                }).build();
    }
}


