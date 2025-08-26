package com.bancario.customerservice.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "customers")
public class Customer {
    public ObjectId id;
    public CustomerType type; // Usando el enum
    public String email;
    public String phone;
}