package com.delcons.features.employee.mapper;

import com.delcons.features.employee.dto.EmployeeCreateDTO;
import com.delcons.features.employee.dto.EmployeeResponseDTO;
import com.delcons.features.employee.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {
    Employee toEntity(EmployeeCreateDTO dto);

    EmployeeCreateDTO toCreateDto(Employee entity);

    EmployeeResponseDTO toResponseDto(Employee entity);

    List<EmployeeResponseDTO> toResponseDtoList(List<Employee> entities);
}
