package com.innovation.transaction_system.transaction_ingestion_service.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .cors(cors -> {})

                .authorizeHttpRequests(auth ->

                        auth

                                .requestMatchers(
                                        "/auth/login",
                                        "/auth/register",
                                        "/dashboard"
                                )

                                .permitAll()

                                .anyRequest()

                                .authenticated()
                );

        return http.build();
    }
}
