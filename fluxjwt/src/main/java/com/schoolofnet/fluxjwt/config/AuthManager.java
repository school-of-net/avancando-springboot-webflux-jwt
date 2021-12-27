package com.schoolofnet.fluxjwt.config;

import com.schoolofnet.fluxjwt.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */
@Component
@RequiredArgsConstructor
public class AuthManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        final String token = authentication.getCredentials().toString();
        final String username = jwtUtil.getUsername(token);
        if (username != null) {
            final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, username);
            return Mono.just(auth);
        }
        return Mono.empty();
    }
}
