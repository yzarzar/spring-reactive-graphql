package com.reactive.graphqltutorial.controller;

import com.reactive.graphqltutorial.model.Player;
import com.reactive.graphqltutorial.model.PlayerInput;
import com.reactive.graphqltutorial.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class PlayerGraphqlController {

    private final PlayerService playerService;

    public PlayerGraphqlController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @MutationMapping("addPlayer")
    public Mono<Player> addPlayer(@Argument PlayerInput playerInput) {
        return this.playerService.addPlayer(playerInput);
    }

    @QueryMapping("getPlayerById")
    public Mono<Player> getPlayerById(@Argument int id) {
        return this.playerService.getPlayerById(id);
    }
}
