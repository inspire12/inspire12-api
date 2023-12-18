package com.inspire12.practice.api.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
//@EnableWebSecurity
@Configuration
public class SecurityConfig {
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
//    private final CustomAccessDeniedHandler customAccessDeniedHandler;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/api/**")
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().hasRole("ADMIN")
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .httpBasic(withDefaults());
//        return http.build();

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

