package com.schoolofnet.fluxjwt.config;

import com.schoolofnet.fluxjwt.handler.AuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */

@Configuration
public class AppConfiguration {

    private final AuthHandler authHandler;

    public AppConfiguration(AuthHandler authHandler) {
        this.authHandler = authHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> auth() {
        return RouterFunctions.route(GET("/sign-up")
                .and(accept(MediaType.APPLICATION_JSON)), this.authHandler::signUp); // handlerFunction
    }
}
