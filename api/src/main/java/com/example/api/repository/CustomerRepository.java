package com.example.api.repository;

import com.example.api.records.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    Flux<Customer> findByName(String name);
}
