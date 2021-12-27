package com.schoolofnet.fluxjwt.config.filter;

import com.schoolofnet.fluxjwt.config.AuthManager;
import com.schoolofnet.fluxjwt.config.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */
@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class SecurityConfigurationFilter {

    private final AuthManager authManager;
    private final SecurityContext securityContext;

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.cors().disable()
                .csrf().disable()
                .authenticationManager(authManager)
                .securityContextRepository(securityContext)
                .authorizeExchange()
                .pathMatchers("/sign-up/**").permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }
}
