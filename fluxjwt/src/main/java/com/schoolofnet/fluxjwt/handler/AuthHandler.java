package com.schoolofnet.fluxjwt.handler;

import com.schoolofnet.fluxjwt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */
@Service
public class AuthHandler {

    private final UserRepository userRepository;

    public AuthHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> signUp(ServerRequest req) {
        return ServerResponse.ok().body(BodyInserters.fromValue("oi"));
    }

}
