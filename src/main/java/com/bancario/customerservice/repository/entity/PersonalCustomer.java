package com.bancario.customerservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalCustomer extends Customer {
    public String firstName;
    public String lastName;
    public String dni;
}
