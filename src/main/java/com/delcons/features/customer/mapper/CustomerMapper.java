package com.delcons.features.customer.mapper;

import com.delcons.features.customer.dto.request.CustomerCreateDTO;
import com.delcons.features.customer.dto.response.CustomerResponseDTO;
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

    public static CustomerResponseDTO toResponseDTO(Customer entity) {
        return new CustomerResponseDTO(
                entity.getId(),
                entity.getDni(),
                entity.getName(),
                entity.getLastname(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getLevel()
        );
    }
}
