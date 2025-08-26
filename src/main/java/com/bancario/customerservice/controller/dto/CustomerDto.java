package com.bancario.customerservice.controller.dto;

import com.bancario.customerservice.repository.entity.CustomerType;
import lombok.Data;

@Data
public class CustomerDto {
    private String id;
    private CustomerType type;
    private String email;
    private String phone;
}
