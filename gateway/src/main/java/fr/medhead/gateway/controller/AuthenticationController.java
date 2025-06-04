package fr.medhead.gateway.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.medhead.gateway.security.JwtUtil;
import lombok.Data;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final ReactiveAuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthenticationController(ReactiveAuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword());
        return authManager.authenticate(auth)
                .map(authentication -> jwtUtil.generateToken(authentication.getName()))
                .map(token -> ResponseEntity.ok(Map.of("token", token)));
    }
}

@Data
class AuthRequest {
    private String username;
    private String password;
}