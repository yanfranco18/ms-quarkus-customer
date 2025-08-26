package com.bancario.customerservice.mapper;

import com.bancario.customerservice.controller.dto.CustomerDto;
import com.bancario.customerservice.repository.entity.Customer;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);

    // --- Métodos de Mapeo de Conversión ---

    // Este método le dice a MapStruct cómo convertir ObjectId a String
    default String mapObjectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    // Este método le dice a MapStruct cómo convertir String a ObjectId
    default ObjectId mapStringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }
}
