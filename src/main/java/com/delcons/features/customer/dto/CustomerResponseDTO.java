package com.delcons.features.customer.dto;

public record CustomerResponseDTO(
        Long id,
        Long dni,
        String name,
        String lastname,
        String email,
        String phone,
        String address,
        String level
) {}
