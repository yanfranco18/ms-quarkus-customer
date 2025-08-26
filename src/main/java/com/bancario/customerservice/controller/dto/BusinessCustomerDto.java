package com.bancario.customerservice.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessCustomerDto extends CustomerDto {
    private String businessName;
    private String ruc;
    private String legalRepresentative;
}
