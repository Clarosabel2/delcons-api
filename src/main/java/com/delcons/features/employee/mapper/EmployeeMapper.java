package com.delcons.features.employee.mapper;

import com.delcons.features.employee.dto.EmployeeCreateDTO;
import com.delcons.features.employee.dto.EmployeeResponseDTO;
import com.delcons.features.employee.model.Employee;

public class EmployeeMapper {
    public static Employee toEntity(EmployeeCreateDTO dto) {
        return new Employee(
                dto.dni(),
                dto.name(),
                dto.lastname(),
                dto.email(),
                dto.phone(),
                dto.address()
        );
    }

    public static EmployeeResponseDTO toResponseDTO(Employee entity) {
        return new EmployeeResponseDTO(
                entity.getId(),
                entity.getDni(),
                entity.getName(),
                entity.getLastname(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getActive()
        );
    }
}
