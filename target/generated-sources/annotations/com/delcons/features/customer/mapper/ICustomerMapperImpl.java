package com.delcons.features.customer.mapper;

import com.delcons.features.customer.dto.request.CustomerCreateDTO;
import com.delcons.features.customer.dto.response.CustomerResponseDTO;
import com.delcons.features.customer.model.Customer;
import com.delcons.shared.dtos.AddressRequestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-11T16:11:02-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class ICustomerMapperImpl implements ICustomerMapper {

    @Override
    public Customer toEntity(CustomerCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        return customer;
    }

    @Override
    public CustomerResponseDTO toResponse(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;

        id = entity.getId();

        Long dni = null;
        String name = null;
        String lastname = null;
        String email = null;
        String phone = null;
        String level = null;
        AddressRequestDTO address = null;

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO( id, dni, name, lastname, email, phone, level, address );

        return customerResponseDTO;
    }
}
