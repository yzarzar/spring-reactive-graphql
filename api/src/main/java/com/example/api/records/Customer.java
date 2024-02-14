package com.example.api.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public record Customer(@JsonProperty("id") @Id Integer id, String name) {
}
