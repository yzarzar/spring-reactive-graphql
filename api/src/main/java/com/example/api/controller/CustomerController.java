package com.example.api.controller;

import com.example.api.records.Customer;
import com.example.api.records.CustomerEvent;
import com.example.api.records.CustomerEventType;
import com.example.api.records.Order;
import com.example.api.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Stream;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @SchemaMapping(typeName = "Customer")
    Flux<Order> orders(Customer customer) {
        var orders = new ArrayList<Order>();
        for (var orderId = 1; orderId <= (Math.random() * 100); orderId++) {
            orders.add(new Order(orderId, customer.id()));
        }
        return Flux.fromIterable(orders);
    }

    @SchemaMapping(typeName = "Query", field = "customers")
    Flux<Customer> customer() {
        return this.customerRepository.findAll();
    }

    @QueryMapping("customerByName")
    Flux<Customer> findCustomerByName(@Argument String name) {
        return this.customerRepository.findByName(name);
    }

    @SchemaMapping(typeName = "Mutation", field = "addCustomer")
    Mono<Customer> addCustomer(@Argument String name) {
        return this.customerRepository.save(new Customer(null,name));
    }

    @SubscriptionMapping("customerEvents")
    Flux<CustomerEvent> customerEvents(@Argument Integer customerId) {
        return this.customerRepository.findById(customerId)
                .flatMapMany(customer -> {
                    var stream = Stream.generate(
                            () -> new CustomerEvent(
                                    customer, (Math.random() > .5 ? CustomerEventType.DELETE : CustomerEventType.UPDATE))
                    );
                    return Flux.fromStream(stream);
                })
                .delayElements(Duration.ofSeconds(1))
                .take(10);
    }
}
