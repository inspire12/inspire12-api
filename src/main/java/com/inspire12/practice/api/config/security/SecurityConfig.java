package com.inspire12.practice.api.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@RequiredArgsConstructor
@EnableWebFluxSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .x509(withDefaults())
                .authorizeExchange(a -> a
                        .pathMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        //.pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
                        .anyExchange().permitAll()
                )
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("user")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .userDetailsService(username -> User.withUsername("user")
                        .password("user")
                        .roles("USER")
                        .build())
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().permitAll()
                ).build();
    }

    //        http
//                .csrf().disable() //
//                .headers().frameOptions().disable() //
////            .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .formLogin().disable()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/actuator/**")
//                .permitAll()
//                .antMatchers("/api/v1/**").permitAll()
//                .antMatchers("/lab/**").permitAll()
//                .anyRequest().authenticated()
////            .and()
////                .exceptionHandling()
////                .authenticationEntryPoint(customAuthenticationEntryPoint)
////                .accessDeniedHandler(customAccessDeniedHandler)
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);
//    }
}


