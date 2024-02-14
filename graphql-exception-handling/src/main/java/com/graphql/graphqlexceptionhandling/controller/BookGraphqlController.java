package com.graphql.graphqlexceptionhandling.controller;

import com.graphql.graphqlexceptionhandling.domain.model.Book;
import com.graphql.graphqlexceptionhandling.domain.model.BookInput;
import com.graphql.graphqlexceptionhandling.domain.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

@Controller
public class BookGraphqlController {

    private final BookService bookService;

    public BookGraphqlController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping("getAllBooks")
    public Flux<Book> getAllBooks() {
        return processWithLog(this.bookService.getAllBooks());
    }

    @SchemaMapping(typeName = "Mutation", field = "addBook")
    public Mono<Book> addBook(@Argument BookInput bookInput) {
        return processWithLog(this.bookService.addBook(bookInput));
    }

    @SchemaMapping(typeName = "Query", field = "getBookById")
    public Mono<Book> getBookById(@Argument Integer id) {
        return processWithLog(this.bookService.getBookById(id));
    }

    @SchemaMapping(typeName = "Mutation", field = "deleteBookById")
    public Mono<Book> deleteBookById(@Argument Integer id) {
        return processWithLog(this.bookService.deleteBookById(id));
    }

    @MutationMapping("updateBook")
    public Mono<Book> updateBooK(@Argument Integer id, @Argument BookInput bookInput) {
        return processWithLog(this.bookService.updateBook(id, bookInput));
    }

    private <T> Flux<T> processWithLog(Flux<T> fluxToLog) {
        return fluxToLog
                .log("BookGraphqlController.", Level.INFO, SignalType.ON_NEXT, SignalType.ON_COMPLETE);
    }

    private <T> Mono<T> processWithLog(Mono<T> monoToLog) {
        return monoToLog
                .log("BookGraphqlController.", Level.INFO, SignalType.ON_NEXT, SignalType.ON_COMPLETE);
    }
}