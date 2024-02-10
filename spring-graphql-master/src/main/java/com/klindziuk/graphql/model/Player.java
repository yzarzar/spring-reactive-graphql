package com.klindziuk.graphql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
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
    private Integer id;

    @Column("name")
    @JsonProperty("name")
    private String name;

    @Column("age")
    @JsonProperty("age")
    private Integer age;

    @Column("club")
    @JsonProperty("club")
    private String club;

    @Column("nationality")
    @JsonProperty("nationality")
    private String nationality;

    public Player(PlayerInput playerInput) {
        this.name = Objects.requireNonNull(playerInput.getName());
        this.age = Objects.requireNonNull(playerInput.getAge());
        this.club = Objects.requireNonNull(playerInput.getClub());
        this.nationality = Objects.requireNonNull(playerInput.getNationality());
    }
}
