package com.graphql.graphqlexceptionhandling.domain.service.impl;

import com.graphql.graphqlexceptionhandling.domain.model.Book;
import com.graphql.graphqlexceptionhandling.domain.model.BookInput;
import com.graphql.graphqlexceptionhandling.domain.repository.BookRepository;
import com.graphql.graphqlexceptionhandling.domain.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Flux<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Mono<Book> addBook(BookInput bookInput) {
        return this.bookRepository.save(new Book(bookInput));
    }

    @Override
    public Mono<Book> deleteBookById(Integer id) {
        return getBookById(id)
                .map(book -> {
                    this.bookRepository.deleteById(id).subscribe();
                    return book;
                });
    }

    @Override
    public Mono<Book> getBookById(Integer id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Mono<Book> updateBook(@Argument Integer id, @Argument BookInput bookInput) {
        return this.bookRepository.findById(Objects.requireNonNull(id))
                .flatMap(book -> {
                    book.setTitle(Objects.requireNonNull(bookInput.getTitle()));
                    book.setAuthor(Objects.requireNonNull(bookInput.getAuthor()));
                    book.setPrice(Objects.requireNonNull(bookInput.getPrice()));
                    return this.bookRepository.save(book).log();
                });
    }
}
