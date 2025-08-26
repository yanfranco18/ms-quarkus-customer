package com.bancario.customerservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessCustomer extends Customer {
    public String businessName;
    public String ruc;
    public String legalRepresentative;
}
