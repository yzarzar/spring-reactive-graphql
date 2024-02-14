package com.graphql.graphqlexceptionhandling.domain.repository;

import com.graphql.graphqlexceptionhandling.domain.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
}
