package com.bancario.customerservice.service.impl;

import com.bancario.customerservice.controller.dto.CustomerDto;
import com.bancario.customerservice.repository.entity.Customer;
import com.bancario.customerservice.mapper.CustomerMapper;
import com.bancario.customerservice.repository.CustomerRepository;
import com.bancario.customerservice.service.CustomerService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerMapper customerMapper;

    @Override
    public Uni<CustomerDto> createCustomer(CustomerDto customerDto) {
        log.info("Creating a new customer of type: {}", customerDto.getType());

        if (customerDto.getType() == null) {
            log.warn("Customer type is missing in the request.");
            return Uni.createFrom().failure(new IllegalArgumentException("Customer type must be specified"));
        }

        Customer customerEntity = customerMapper.toEntity(customerDto);
        return customerRepository.persist(customerEntity)
                .onItem().invoke(persistedCustomer -> log.info("Customer with ID {} created successfully", persistedCustomer.id))
                .onFailure().invoke(throwable -> log.error("Failed to create customer: {}", throwable.getMessage(), throwable))
                .onItem().transform(customer -> customerMapper.toDto(customer));
    }

    @Override
    public Multi<CustomerDto> findAllCustomers() {
        log.info("Fetching all customers from the database");
        return customerRepository.findAll().stream()
                .onItem().transform(customer -> customerMapper.toDto(customer))
                .onCompletion().invoke(() -> log.info("Finished fetching all customers"));
    }

    @Override
    public Uni<CustomerDto> findCustomerById(String id) {
        log.info("Attempting to find customer by ID: {}", id);

        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid customer ID format: {}", id);
            return Uni.createFrom().failure(new IllegalArgumentException("Invalid customer ID format"));
        }

        return customerRepository.findById(objectId)
            .onItem().ifNotNull().invoke(customer -> log.info("Customer with ID {} found", id))
            .onItem().transform(customer -> {
                if (customer == null) {
                    log.warn("Customer with ID {} not found", id);
                    return null;
                }
                return customerMapper.toDto(customer);
            });
    }

    @Override
    public Uni<CustomerDto> updateCustomer(String id, CustomerDto customerDto) {
        log.info("Updating customer with ID: {}", id);

        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid customer ID format: {}", id);
            return Uni.createFrom().failure(new IllegalArgumentException("Invalid customer ID format"));
        }

        return customerRepository.findById(objectId)
                .onItem().transformToUni(existingCustomer -> {
                    if (existingCustomer == null) {
                        log.warn("Update failed: Customer with ID {} not found", id);
                        return Uni.createFrom().failure(new RuntimeException("Customer not found"));
                    }

                    Customer updatedCustomer = customerMapper.toEntity(customerDto);
                    updatedCustomer.id = existingCustomer.id;

                    return customerRepository.update(updatedCustomer)
                            .onItem().invoke(persistedCustomer -> log.info("Customer with ID {} updated successfully", persistedCustomer.id))
                            .onFailure().invoke(throwable -> log.error("Failed to update customer with ID {}: {}", id, throwable.getMessage(), throwable))
                            .onItem().transform(customer -> customerMapper.toDto(customer));
                });
    }

    @Override
    public Uni<Void> deleteCustomer(String id) {
        log.info("Deleting customer with ID: {}", id);

        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            log.warn("Invalid customer ID format: {}", id);
            return Uni.createFrom().failure(new IllegalArgumentException("Invalid customer ID format"));
        }

        return customerRepository.deleteById(objectId)
                .onItem().invoke(isDeleted -> {
                    if (isDeleted) {
                        log.info("Customer with ID {} deleted successfully", id);
                    } else {
                        log.warn("Deletion failed: Customer with ID {} not found", id);
                    }
                })
                .replaceWithVoid();
    }
}