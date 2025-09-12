package com.bancario.customerservice.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@MongoEntity(collection = "customers")
public class Customer {
    public ObjectId id;
    public CustomerType type;
    public String email;
    public String phone;

    // Campos para Clientes Personales
    public String firstName;
    public String lastName;
    public String dni;

    // Campos para Clientes Empresariales
    public String businessName;
    public String ruc;
    public String legalRepresentative;
}