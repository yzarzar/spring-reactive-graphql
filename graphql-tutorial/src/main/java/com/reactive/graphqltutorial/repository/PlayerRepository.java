package com.reactive.graphqltutorial.repository;

import com.reactive.graphqltutorial.model.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PlayerRepository extends ReactiveCrudRepository<Player, Integer> {
}