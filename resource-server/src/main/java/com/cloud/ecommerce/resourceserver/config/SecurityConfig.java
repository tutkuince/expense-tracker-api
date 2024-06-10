package com.cloud.ecommerce.resourceserver.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(List.of("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }));

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers(
                                        "/api/shop/brands",
                                        "/api/shop/categories",
                                        "/api/shop/products",
                                        "/api/shop/products/**",
                                        "/basket",
                                        "basket/**"
                                )
                                .permitAll()
                                // .requestMatchers("/api/orders").authenticated())
                                .requestMatchers("/api/orders").hasRole("ADMIN"))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)));

        return http.build();
    }
}
