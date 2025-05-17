package com.example.apiGateway.apiGateway.filter;

import com.example.apiGateway.apiGateway.config.JwtConfig;
import com.example.apiGateway.apiGateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, JwtConfig jwtConfig) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            
            // Skip authentication for login and register endpoints
            if (path.contains("/auth/login") || path.contains("/auth/register")) {
                return chain.filter(exchange);
            }

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }
            String token = extractToken(exchange.getRequest());

            if (token == null) {
                return onError(exchange, "Missing JWT", HttpStatus.UNAUTHORIZED);
            }

            if (!jwtUtil.validateToken(token)) {
                return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
            }

            String username = jwtUtil.extractUsername(token);
            exchange.getRequest().mutate()
                    .header("X-Auth-User", username)
                    .build();

            return chain.filter(exchange);
        };
    }

    private String extractToken(ServerHttpRequest request) {
        // 1. Check cookie
        if (request.getCookies().containsKey("jwt")) {
            return request.getCookies().getFirst("jwt").getValue();
        }
        // 2. Check Authorization header
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(jwtConfig.getPrefix())) {
            return authHeader.replace(jwtConfig.getPrefix(), "").trim();
        }
        return null;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Put configuration properties here if needed
    }
} 