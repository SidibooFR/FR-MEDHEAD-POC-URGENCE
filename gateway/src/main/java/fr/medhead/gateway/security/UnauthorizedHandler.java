package fr.medhead.gateway.security;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UnauthorizedHandler implements ServerAuthenticationEntryPoint {

    private static final String ERROR_JSON_TEMPLATE = "{\"error\":\"%s\"}";

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String errorJson = String.format(ERROR_JSON_TEMPLATE, ex.getMessage());
        DataBuffer buffer = response.bufferFactory().wrap(errorJson.getBytes());

        return response.writeWith(Mono.just(buffer));
    }
}