package com.bancario.customerservice.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonalCustomerDto extends CustomerDto {
    private String firstName;
    private String lastName;
    private String dni;
}