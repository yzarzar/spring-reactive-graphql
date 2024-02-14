// Book.java
package com.graphql.graphqlexceptionhandling.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("price")
    private double price;

    public Book(BookInput bookInput) {
        this.title = Objects.requireNonNull(bookInput.getTitle());
        this.author = Objects.requireNonNull(bookInput.getAuthor());
        this.price = Objects.requireNonNull(bookInput.getPrice());
    }
}
