package fr.medhead.gateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class JwtUtil {

    private static final String SECRET = System.getenv()
            .getOrDefault("JWT_SECRET", "change-me-32-byte-min-secret-key-123456");
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    private static final Duration EXPIRATION = Duration.ofHours(1);

    /**
     * Génère un JWT contenant le username comme <sub>subject</sub> et une durée de
     * vie de 1 h.
     */
    public String generateToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(EXPIRATION)))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrait le username (subject) depuis le token.
     */
    public Mono<String> extractUsername(String token) {
        return Mono.fromRunnable(() -> Jwts.parser()
                .setSigningKey(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());
    }
}