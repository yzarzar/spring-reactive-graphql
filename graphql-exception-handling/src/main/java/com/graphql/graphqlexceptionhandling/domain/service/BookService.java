package com.graphql.graphqlexceptionhandling.domain.service;

import com.graphql.graphqlexceptionhandling.domain.model.Book;
import com.graphql.graphqlexceptionhandling.domain.model.BookInput;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Flux<Book> getAllBooks();
    Mono<Book> addBook(BookInput bookInput);
    Mono<Book> deleteBookById(Integer id);
    Mono<Book> getBookById(Integer id);
    Mono<Book> updateBook(Integer id, BookInput bookInput);
}
