package com.bancario.customerservice.repository;

import com.bancario.customerservice.repository.entity.Customer;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements ReactivePanacheMongoRepository<Customer> {
}