package com.delcons.features.customer.dto.response;

import com.delcons.shared.dtos.AddressRequestDTO;

public record CustomerResponseDTO(
        Long id,
        Long dni,
        String name,
        String lastname,
        String email,
        String phone,
        String level,
        AddressRequestDTO address
) {}
