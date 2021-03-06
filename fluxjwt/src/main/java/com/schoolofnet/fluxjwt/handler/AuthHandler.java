package com.schoolofnet.fluxjwt.handler;

import com.schoolofnet.fluxjwt.model.document.User;
import com.schoolofnet.fluxjwt.repository.UserRepository;
import com.schoolofnet.fluxjwt.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequiredArgsConstructor
public class AuthHandler {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public Mono<ServerResponse> signUp(ServerRequest req) {
        Mono<User> userMono = req.bodyToMono(User.class);
        return userMono
                .map(u -> {
                    final User userPass = new User(u.getUsername(), u.getPassword());
                    userPass.setPassword(encoder.encode(u.getPassword()));
                    return userPass;
                })
                .flatMap(this.userRepository::save)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));

    }

    public Mono<ServerResponse> login(ServerRequest req) {
        Mono<User> userMono = req.bodyToMono(User.class);
        return userMono.flatMap(u -> userRepository.findByUsername(u.getUsername())
                .flatMap(user -> {
                    if (encoder.matches(u.getPassword(), user.getPassword())) {
                        return ServerResponse.ok().body(BodyInserters.fromValue(jwtUtil.getToken(user)));
                    }
                    return ServerResponse.badRequest().body(BodyInserters.fromValue("Invalid credentials"));
                }).switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromValue("User does not exists"))));
    }
}
