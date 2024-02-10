package com.yarzar.graphqlfirstapi.service.impl;

import com.yarzar.graphqlfirstapi.exceptions.EntityMappingException;
import com.yarzar.graphqlfirstapi.model.Player;
import com.yarzar.graphqlfirstapi.model.PlayerInput;
import com.yarzar.graphqlfirstapi.repository.PlayerRepository;
import com.yarzar.graphqlfirstapi.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Mono<Player> getPlayerById(Integer id) {
        final String errorMessage = String.format("This is no player with id: '%d'", id);
        return processWithErrorCheck(this.playerRepository.findById(id), errorMessage);
    }

    @Override
    public Mono<Player> getPlayerByName(String name) {
        final String errorMessage = String.format("There is no player with name : '%s'", name);
        return processWithErrorCheck(this.playerRepository.findByName(name), errorMessage);
    }

    @Override
    public Flux<Player> getAllPlayers() {
        final String errorMessage = "There is an issue getting all of players.";
        return processWithErrorCheck(this.playerRepository.findAll(), errorMessage);
    }

    @Override
    public Flux<Player> getPlayersByClub(String club) {
        final String errorMessage = String.format("There is no player for club : '%s'", club);
        return processWithErrorCheck(this.playerRepository.findByClub(club), errorMessage);
    }

    @Override
    public Flux<Player> getPlayersByNationality(String nationality) {
        final String errorMessage = String.format("There is no players with nationality : '%s'", nationality);
        return processWithErrorCheck(this.playerRepository.findByNationality(nationality), errorMessage);
    }

    @Override
    public Mono<Player> addPlayer(PlayerInput playerInput) {
        final String errorMessage = "Unable to add player with input : " + playerInput;
        return processWithErrorCheck(this.playerRepository.save(new Player(playerInput)), errorMessage);
    }

    @Override
    public Mono<Player> updatePlayer(@Argument Integer id, @Argument PlayerInput playerInput) {
        final String errorMessage =
                "Unable to update player with id" + id + "input:" + playerInput;
        return processWithErrorCheck(this.playerRepository.findById(Objects.requireNonNull(id)),
                errorMessage)
                .flatMap(player -> {
                    player
                            .setName(Objects.requireNonNull(playerInput.getName()))
                            .setAge(Objects.requireNonNull(playerInput.getAge()))
                            .setClub(Objects.requireNonNull(playerInput.getClub()))
                            .setClub(Objects.requireNonNull(playerInput.getClub()));
                    return this.playerRepository.save(player).log();
                });
    }

    @Override
    public Mono<Player> deletePlayerById(Integer id) {
        return getPlayerById(id)
                .map((player) -> {
                    this.playerRepository.deleteById(id).subscribe();
                    return player;
                });
    }

    @Override
    public Mono<Player> deletePlayerByName(String name) {
        return getPlayerByName(name)
                .map(player -> {
                    this.playerRepository.deleteByName(name).subscribe();
                    return player;
                });
    }

    private <T> Mono<T> processWithErrorCheck(Mono<T> monoCheck, String errorMessage) {
        return monoCheck.switchIfEmpty(Mono.defer(() -> {
            log.error(errorMessage);
            return Mono.error(new EntityMappingException(errorMessage));
        }));
    }

    private <T> Flux<T> processWithErrorCheck(Flux<T> fluxCheck, String errorMessage) {
        return fluxCheck.switchIfEmpty(Flux.defer(() -> {
            log.error(errorMessage);
            return Mono.error(new EntityMappingException(errorMessage));
        }));
    }
}
