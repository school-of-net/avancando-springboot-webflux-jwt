package com.schoolofnet.fluxjwt.handler;

import com.schoolofnet.fluxjwt.model.document.User;
import com.schoolofnet.fluxjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository userRepository;

    public AuthHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> signUp(ServerRequest req) {
        Mono<User> userMono = req.bodyToMono(User.class);
        return userMono
                .map(u -> new User(u.getUsername(), u.getPassword()))
                .flatMap(this.userRepository::save)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));

    }
}
