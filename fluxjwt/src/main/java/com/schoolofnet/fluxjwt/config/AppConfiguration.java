package com.schoolofnet.fluxjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */

@Configuration
public class AppConfiguration {

    @Bean
    public RouterFunction auth() {
        return RouterFunctions.route(POST("/sign-up")
                .and(accept(MediaType.APPLICATION_JSON)), null); // handlerFunction
    }
}
