package com.reactive.graphqltutorial.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInput {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;
}
