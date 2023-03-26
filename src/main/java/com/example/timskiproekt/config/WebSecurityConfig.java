package com.example.timskiproekt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    private final CustomEmailPasswordAuthenticationProvider customEmailPasswordAuthenticationProvider;

    public WebSecurityConfig(CustomEmailPasswordAuthenticationProvider customEmailPasswordAuthenticationProvider) {
        this.customEmailPasswordAuthenticationProvider = customEmailPasswordAuthenticationProvider;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/cities/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/categories/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api")).permitAll()

                )
                .headers(headers -> headers.frameOptions().disable())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/cities/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/categories/**"))

                );
        http.authenticationProvider(customEmailPasswordAuthenticationProvider);
        return http.build();
    }
}