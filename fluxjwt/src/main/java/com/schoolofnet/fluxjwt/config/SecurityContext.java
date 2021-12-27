package com.schoolofnet.fluxjwt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */
@Component
@RequiredArgsConstructor
public class SecurityContext implements ServerSecurityContextRepository {

    private final AuthManager authManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange,
                           org.springframework.security.core.context.SecurityContext context) {
        return null;
    }

    @Override
    public Mono<org.springframework.security.core.context.SecurityContext> load(ServerWebExchange exchange) {
        final ServerHttpRequest request = exchange.getRequest();
        final String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.replace("Bearer ", "");
        }

        if (token != null) {
            final Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
            return authManager.authenticate(auth).map(SecurityContextImpl::new);
        }

        return Mono.empty();
    }
}
