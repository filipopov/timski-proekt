package com.example.timskiproekt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/cities/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/categories/**")).permitAll()

                )
                .headers(headers -> headers.frameOptions().disable())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/cities/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/categories/**"))

                );
        return http.build();
    }
}