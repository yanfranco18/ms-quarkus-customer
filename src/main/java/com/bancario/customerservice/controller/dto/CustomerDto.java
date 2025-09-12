package com.bancario.customerservice.controller.dto;

import com.bancario.customerservice.repository.entity.CustomerType;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CustomerDto {
    private String id;
    @NotNull
    private CustomerType type;
    @Email
    private String email;
    @NotBlank
    private String phone;

    // Campos para Clientes Personales
    private String firstName;
    private String lastName;
    private String dni;

    // Campos para Clientes Empresariales
    private String businessName;
    private String ruc;
    private String legalRepresentative;
}