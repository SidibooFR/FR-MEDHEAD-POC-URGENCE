package fr.medhead.gateway.security;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TokenPropagationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return exchange.getPrincipal()
                .filter(principal -> principal instanceof Authentication)
                .cast(Authentication.class)
                .flatMap(auth -> {
                    if (auth instanceof JwtAuthenticationToken) {
                        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) auth;
                        exchange.getRequest().mutate()
                                .header("X-Auth-Token", jwtAuth.getToken().getTokenValue());
                    }
                    return chain.filter(exchange);
                });
    }
}