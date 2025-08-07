package com.delcons.features.employee.dto;

import com.delcons.shared.dtos.AddressRequestDTO;

public record EmployeeResponseDTO(
        Long id,
        Long dni,
        String name,
        String lastname,
        String email,
        String phone,
        boolean active,
        AddressRequestDTO address
) {
}
