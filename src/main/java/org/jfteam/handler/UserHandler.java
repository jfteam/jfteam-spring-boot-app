package org.jfteam.handler;

import org.jfteam.domain.User;
import org.jfteam.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-08-16
 * Time: 下午6:32
 */
@Component
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        final Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().body(userRepository.save(userMono));
    }

    public Mono<ServerResponse> findAll() {
        final List<User> userList = userRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(Flux.fromStream(userList.stream()), User.class);
    }
}
