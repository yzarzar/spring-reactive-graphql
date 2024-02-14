package com.example.exceptionhandlingwebflux.springreactiveexceptions.handler;

import com.example.exceptionhandlingwebflux.springreactiveexceptions.client.DemoSelfClient;
import com.example.exceptionhandlingwebflux.springreactiveexceptions.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TriggerHandler implements HandlerFunction<ServerResponse> {

    @Autowired
    private DemoSelfClient demoSelfClient;

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(demoSelfClient.fetch(), Users.class);
    }
}