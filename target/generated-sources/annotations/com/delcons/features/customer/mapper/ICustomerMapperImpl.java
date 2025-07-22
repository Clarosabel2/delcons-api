package com.delcons.features.customer.mapper;

import com.delcons.features.customer.dto.request.CustomerCreateDTO;
import com.delcons.features.customer.dto.response.CustomerResponseDTO;
import com.delcons.features.customer.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-17T23:00:21-0300",
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

        customer.setDni( dto.dni() );
        customer.setName( dto.name() );
        customer.setLastname( dto.lastname() );
        customer.setEmail( dto.email() );
        customer.setPhone( dto.phone() );
        customer.setAddress( dto.address() );

        return customer;
    }

    @Override
    public CustomerResponseDTO toResponse(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        Long dni = null;
        String name = null;
        String lastname = null;
        String email = null;
        String phone = null;
        String address = null;
        String level = null;

        id = entity.getId();
        dni = entity.getDni();
        name = entity.getName();
        lastname = entity.getLastname();
        email = entity.getEmail();
        phone = entity.getPhone();
        address = entity.getAddress();
        level = entity.getLevel();

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO( id, dni, name, lastname, email, phone, address, level );

        return customerResponseDTO;
    }
}
