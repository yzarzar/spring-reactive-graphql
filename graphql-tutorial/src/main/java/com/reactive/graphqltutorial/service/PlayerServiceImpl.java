package com.reactive.graphqltutorial.service;

import com.reactive.graphqltutorial.model.Player;
import com.reactive.graphqltutorial.model.PlayerInput;
import com.reactive.graphqltutorial.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Mono<Player> addPlayer(PlayerInput playerInput) {
        return this.playerRepository.save(new Player(playerInput));
    }

    @Override
    public Mono<Player> getPlayerById(int id) {
        return this.playerRepository.findById(id);
    }
}
