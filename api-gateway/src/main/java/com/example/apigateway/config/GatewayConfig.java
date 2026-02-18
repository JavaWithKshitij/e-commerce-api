package com.example.apigateway.config;

import com.example.apigateway.filter.JwtHandlerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes(JwtHandlerFilter jwtFilter) {
        return route("auth_service")
                .GET("/auth/**", http("http://localhost:9083")) // No filter for auth
                .build()
                .and(route("order_service")
                        .GET("/orders/**", http("http://localhost:9082"))
                        .filter(jwtFilter.validateJwt()) // Apply the bouncer here
                        .build());
    }
}
