package com.yarzar.graphqlfirstapi.repository;

import com.yarzar.graphqlfirstapi.model.Player;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveCrudRepository<Player, Integer> {

    Mono<Player> findByName(String name);
    Mono<Player> deleteByName(String name);
    Flux<Player> findByClub(String club);
    Flux<Player> findByNationality(String nationality);
}
