package com.delcons.features.customer.mapper;

import com.delcons.features.customer.dto.CustomerCreateDTO;
import com.delcons.features.customer.model.Customer;

public class CustomerMapper {
    public static Customer toEntity(CustomerCreateDTO dto) {
        return new Customer(
                dto.dni(),
                dto.name(),
                dto.lastname(),
                dto.email(),
                dto.phone(),
                dto.address(),
                "standard"
        );
    }
}
