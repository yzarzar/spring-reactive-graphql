package com.example.exceptionhandlingwebflux.springreactiveexceptions.config;

import com.example.exceptionhandlingwebflux.springreactiveexceptions.handler.DataProcessingHandler;
import com.example.exceptionhandlingwebflux.springreactiveexceptions.handler.TriggerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

    @Autowired
    private DataProcessingHandler dataProcessingHandler;
    @Autowired
    private TriggerHandler triggerHandler;

    @Bean
    public RouterFunction<ServerResponse> getRoutes() {
        return RouterFunctions
                .route()
                .POST("/1.0/process", dataProcessingHandler)
                .POST("/1.0/trigger", triggerHandler)
                .build();
    }
}
