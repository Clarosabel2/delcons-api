package com.delcons.features.employee.dto;

public record EmployeeResponseDTO(
        Long id,
        Long dni,
        String name,
        String lastname,
        String email,
        String phone,
        String address,
        boolean active
) {
}
