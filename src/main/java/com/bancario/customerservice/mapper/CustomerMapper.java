package com.bancario.customerservice.mapper;

import com.bancario.customerservice.controller.dto.CustomerDto;
import com.bancario.customerservice.repository.entity.Customer;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerDto dto);

    CustomerDto toDto(Customer customer);

    // Métodos de conversión para ObjectId
    default String mapObjectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    default ObjectId mapStringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }
}