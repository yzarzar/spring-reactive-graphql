package com.reactive.graphqltutorial.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@Table("player")
public class Player {

    @Id
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    public Player(PlayerInput playerInput) {
        this.name = Objects.requireNonNull(playerInput.getName());
    }
}
