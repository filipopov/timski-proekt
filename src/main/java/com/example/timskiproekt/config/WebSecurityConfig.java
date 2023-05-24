package com.example.timskiproekt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomEmailPasswordAuthenticationProvider customEmailPasswordAuthenticationProvider;

    public WebSecurityConfig(CustomEmailPasswordAuthenticationProvider customEmailPasswordAuthenticationProvider) {
        this.customEmailPasswordAuthenticationProvider = customEmailPasswordAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize.anyRequest().permitAll()).csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}