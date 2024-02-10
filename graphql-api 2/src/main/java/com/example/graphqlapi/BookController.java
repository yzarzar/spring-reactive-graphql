package com.example.graphqlapi;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class BookController {
    @QueryMapping
    public Mono<Book> bookById(@Argument String id) {
        return Mono.fromCallable(() -> Book.getById(id))
                .flatMap(book -> Mono.justOrEmpty(book))
                .switchIfEmpty(Mono.error(new Exception("Book not found with id: " + id)));
    }

    @SchemaMapping
    public Mono<Author> author(Book book) {
        return Mono.fromCallable(() -> Author.getById(book.authorId()))
                .flatMap(author -> Mono.justOrEmpty(author))
                .switchIfEmpty(Mono.error(new Exception("Author not found for book with id: " + book.id())));
    }
}