package fr.medhead.gateway.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private UnauthorizedHandler unauthorizedHandler;

    /**
     * Utilisateur in‑memory : *admin / password*.
     */
    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.withUsername("admin")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public GlobalFilter globalFilter() {
        return new TokenPropagationFilter();
    }

    /**
     * Gestionnaire d’authentification utilisé par AuthenticationController.
     */
    @Bean
    public ReactiveAuthenticationManager authenticationManager(MapReactiveUserDetailsService uds,
            PasswordEncoder encoder) {
        UserDetailsRepositoryReactiveAuthenticationManager auth = new UserDetailsRepositoryReactiveAuthenticationManager(
                uds);
        auth.setPasswordEncoder(encoder);
        return auth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
            JwtAuthenticationFilter jwtFilter) {
        return http
                .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**").permitAll() // routes publiques
                        .anyExchange().authenticated()) // tout le reste est protégé
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION) // filtre JWT avant l’authentification
                .build();
    }
}
