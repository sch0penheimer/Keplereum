package com.example.apiGateway.apiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeExchange(auth -> auth
                .pathMatchers("/api/v1/users/auth/**").permitAll()
                .anyExchange().authenticated()
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());
            
        return http.build();
    }
} 