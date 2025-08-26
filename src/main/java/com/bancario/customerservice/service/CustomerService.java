package com.bancario.customerservice.service;

import com.bancario.customerservice.controller.dto.CustomerDto;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;

public interface CustomerService {
    Uni<CustomerDto> createCustomer(CustomerDto customerDto);
    Multi<CustomerDto> findAllCustomers();
    Uni<CustomerDto> findCustomerById(String id);
    Uni<CustomerDto> updateCustomer(String id, CustomerDto customerDto);
    Uni<Void> deleteCustomer(String id);
}
