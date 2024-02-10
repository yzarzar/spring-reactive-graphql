package com.graphql.queries.controller;

import com.graphql.queries.records.Account;
import com.graphql.queries.records.Customer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class MyGraphqlController {

    private final List<Customer> listCustomers = List.of(
            new Customer(1, "Josh Long"),
            new Customer(2, "Yar Zar"),
            new Customer(2, "John Doe")
    );

    @QueryMapping
    public String helloWithName(@Argument String name) {
        return "Hello, " + name + "!";
    }

    @SchemaMapping(typeName = "Query", field = "hello")
    public String hello() {
        return "Hello, Yar Zar";
    }

    @QueryMapping
    public Customer getCustomer(@Argument int id) {
        return new Customer(id, Math.random() > .5? "A" : "B");
    }

    @QueryMapping("listAllCustomers")
    public Flux<Customer> listAllCustomers() {
        return Flux.fromIterable(this.listCustomers);
    }

    @SchemaMapping(typeName = "Customer", field = "account")
    public Mono<Account> customers(Customer customer) {
        return Mono.just(new Account(customer.id()));
    }
}
