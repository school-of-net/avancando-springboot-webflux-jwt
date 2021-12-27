package com.schoolofnet.fluxjwt.config.filter;

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
@EnableWebFluxSecurity
public class SecurityConfigurationFilter {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.cors().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(new String[]{"/sign-up/**"}).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }
}
