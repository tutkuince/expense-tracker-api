package com.cloud.ecommerce.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (authorize) -> authorize
                        .requestMatchers(
                                "/api/shop/brands",
                                "/api/shop/categories",
                                "/api/shop/products",
                                "/api/shop/products/**"
                                )
                        .permitAll());
        return http.build();
    }
}
