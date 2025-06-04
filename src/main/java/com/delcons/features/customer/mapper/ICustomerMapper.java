package com.delcons.features.customer.mapper;

import com.delcons.features.customer.dto.request.CustomerCreateDTO;
import com.delcons.features.customer.dto.response.CustomerResponseDTO;
import com.delcons.features.customer.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    Customer toEntity(CustomerCreateDTO dto);
    CustomerResponseDTO toResponse(Customer entity);
}
