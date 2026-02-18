package com.example.apigateway.filter;

import com.example.apigateway.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class JwtHandlerFilter {

    public HandlerFilterFunction<ServerResponse, ServerResponse> validateJwt() {
        return (request, next) -> {
            // 1. Get Authorization Header
            String authHeader = request.headers().firstHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ServerResponse.status(HttpStatus.UNAUTHORIZED).body("Missing Token");
            }

            String token = authHeader.substring(7);
            try {
                // 2. Validate using Utils
                JwtUtils.validateToken(token);
                return next.handle(request); // Continue to downstream service
            } catch (Exception e) {
                return ServerResponse.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
            }
        };
    }
}