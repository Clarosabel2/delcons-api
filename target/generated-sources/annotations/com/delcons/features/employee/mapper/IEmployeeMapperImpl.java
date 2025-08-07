package com.delcons.features.employee.mapper;

import com.delcons.features.employee.dto.EmployeeCreateDTO;
import com.delcons.features.employee.dto.EmployeeResponseDTO;
import com.delcons.features.employee.enums.BusinessArea;
import com.delcons.features.employee.model.Employee;
import com.delcons.shared.dtos.AddressRequestDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-06T20:40:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class IEmployeeMapperImpl implements IEmployeeMapper {

    @Override
    public Employee toEntity(EmployeeCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setArea( dto.area() );

        return employee;
    }

    @Override
    public EmployeeCreateDTO toCreateDto(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        BusinessArea area = null;

        area = entity.getArea();

        Long dni = null;
        String name = null;
        String lastname = null;
        String email = null;
        String phone = null;
        AddressRequestDTO address = null;

        EmployeeCreateDTO employeeCreateDTO = new EmployeeCreateDTO( dni, name, lastname, email, phone, area, address );

        return employeeCreateDTO;
    }

    @Override
    public EmployeeResponseDTO toResponseDto(Employee entity) {
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
        boolean active = false;
        AddressRequestDTO address = null;

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO( id, dni, name, lastname, email, phone, active, address );

        return employeeResponseDTO;
    }

    @Override
    public List<EmployeeResponseDTO> toResponseDtoList(List<Employee> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EmployeeResponseDTO> list = new ArrayList<EmployeeResponseDTO>( entities.size() );
        for ( Employee employee : entities ) {
            list.add( toResponseDto( employee ) );
        }

        return list;
    }
}
