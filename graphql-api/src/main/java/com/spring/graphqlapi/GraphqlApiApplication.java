package com.spring.graphqlapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@SpringBootApplication
public class GraphqlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApiApplication.class, args);
	}

}

@Controller
class CustomerGraphqlController {

	private final CustomerRepository customerRepository;

	CustomerGraphqlController(CustomerRepository customerRepository) {
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
	Flux<Customer> customers() {
		return this.customerRepository.findAll();
	}

	@QueryMapping("customerByName")
	Flux<Customer> findByName(@Argument String name) {
		return this.customerRepository.findByName(name);
	}

	@SchemaMapping(typeName = "Mutation", field = "addCustomer")
	Mono<Customer> addCustomer(@Argument String name) {
		return this.customerRepository.save(new Customer(null, name));
	}
}

record Order(int id, int customerId) {}

record Customer(@JsonProperty("id") @Id Integer id, @JsonProperty("name") String name) {}

interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
	Flux<Customer> findByName(String name);
}
