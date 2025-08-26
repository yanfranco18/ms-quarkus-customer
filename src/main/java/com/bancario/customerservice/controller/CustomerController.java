package com.bancario.customerservice.controller;

import com.bancario.customerservice.controller.dto.CustomerDto;
import com.bancario.customerservice.service.CustomerService;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class CustomerController {

    @Inject
    CustomerService customerService;

    @POST
    public Uni<Response> create(CustomerDto customerDto) {
        log.info("Creating customer: {}", customerDto);
        return customerService.createCustomer(customerDto)
                .onItem().transform(dto -> {
                    log.info("Customer created: {}", dto);
                    return Response.status(Response.Status.CREATED).entity(dto).build();
                });
    }

    @GET
    public Multi<CustomerDto> findAll() {
        log.info("Fetching all customers");
        return customerService.findAllCustomers();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> findById(@PathParam("id") String id) {
        log.info("Fetching customer by id: {}", id);
        return customerService.findCustomerById(id)
                .onItem().ifNotNull().transform(dto -> {
                    log.info("Customer found: {}", dto);
                    return Response.ok(dto).build();
                })
                .onItem().ifNull().continueWith(() -> {
                    log.warn("Customer not found with id: {}", id);
                    return Response.status(Response.Status.NOT_FOUND).build();
                });
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") String id, CustomerDto customerDto) {
        log.info("Updating customer with id: {}", id);
        return customerService.updateCustomer(id, customerDto)
                .onItem().ifNotNull().transform(dto -> {
                    log.info("Customer updated: {}", dto);
                    return Response.ok(dto).build();
                })
                .onItem().ifNull().continueWith(() -> {
                    log.warn("Customer not found for update with id: {}", id);
                    return Response.status(Response.Status.NOT_FOUND).build();
                });
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") String id) {
        log.info("Deleting customer with id: {}", id);
        return customerService.deleteCustomer(id)
                .onItem().transform(v -> {
                    log.info("Customer deleted with id: {}", id);
                    return Response.status(Response.Status.NO_CONTENT).build();
                });
    }
}
