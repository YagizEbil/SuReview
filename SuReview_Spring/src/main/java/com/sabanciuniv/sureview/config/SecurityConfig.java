package com.sabanciuniv.sureview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity> {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/courses/**").permitAll() // Allow access to /api/courses
                        .anyRequest().authenticated()) // Any other request needs authentication
                .formLogin(formLogin -> formLogin.permitAll()); // Allow form login
        return http.build();
    }
}