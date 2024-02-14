package com.graphql.batch.controller;

import com.graphql.batch.records.Account;
import com.graphql.batch.records.Customer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class BatchController {

    @SchemaMapping(typeName = "Query", field = "customers")
    public Flux<Customer> customers() {
        return Flux.fromIterable(List.of(
                new Customer(1, "A"),
                new Customer(2, "B"),
                new Customer(3, "C")
        ));
    }

    @BatchMapping("account")
    public Map<Customer, Account> account(List<Customer> customers) {
        return customers
                .stream()
                .collect(Collectors.toMap(customer -> customer,
                        customer -> new Account(customer.id())));
    }

//    @SchemaMapping(typeName = "Customer", field = "account")
//    public Mono<Account> account(Customer customer) {
//        return Mono.just(new Account(customer.id()));
//    }
}
