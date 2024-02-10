package com.reactive.graphqltutorial.service;

import com.reactive.graphqltutorial.model.Player;
import com.reactive.graphqltutorial.model.PlayerInput;
import reactor.core.publisher.Mono;

public interface PlayerService {

    Mono<Player> addPlayer(PlayerInput playerInput);

    Mono<Player> getPlayerById(int id);
}
