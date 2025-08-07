package com.delcons.features.employee.service;

import com.delcons.features.employee.dto.EmployeeCreateDTO;
import com.delcons.features.employee.dto.EmployeeResponseDTO;
import com.delcons.features.employee.mapper.IEmployeeMapper;
import com.delcons.features.employee.model.Employee;
import com.delcons.features.employee.repository.EmployeeRepository;
import com.delcons.shared.models.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository empRepo;
    private final IEmployeeMapper mapper;

    public EmployeeService(
            EmployeeRepository empRepo,
            IEmployeeMapper mapper
    ) {
        this.empRepo = empRepo;
        this.mapper = mapper;
    }

    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        return empRepo.findAll(pageable).map(mapper::toResponseDto);
    }

    public Page<EmployeeResponseDTO> getEmployeesByActive(Pageable pageable, boolean active) {
        return empRepo.findAllByPerson_Active(pageable, active).map(mapper::toResponseDto);
    }

    public EmployeeResponseDTO getEmployeeByDni(long dni) {
        Optional<Employee> result = empRepo.findEmployeesByPerson_Dni(dni);
        return verifyEmployee(result, "dni", String.valueOf(dni));
    }

    public EmployeeResponseDTO getEmployeeByEmail(String email) {
        Optional<Employee> result = empRepo.findByPerson_Email(email);
        return verifyEmployee(result, "email", email);
    }

    private EmployeeResponseDTO verifyEmployee(Optional<Employee> result, String attribute, String valueToSearch) {
        Employee employee = result.orElseThrow(() ->
                new EntityNotFoundException("Employee with " + attribute + " : " + valueToSearch + " not found")
        );
        return mapper.toResponseDto(employee);
    }

    public EmployeeResponseDTO saveEmployee(EmployeeCreateDTO emp) {
        Employee newEmp = mapper.toEntity(emp);
        return mapper.toResponseDto(empRepo.save(newEmp));
    }

    public EmployeeResponseDTO updateEmployee(EmployeeCreateDTO emp) {
        Employee empToUpdate = empRepo.findById(emp.dni()).orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + emp.dni()));
        Person person = empToUpdate.getPerson();
        person.setDni(emp.dni());
        person.setName(emp.name());
        person.setEmail(emp.email());
//        person.setAddress(emp.address());
        person.setLastname(emp.lastname());
//        person.setPhone(emp.phone());
        empToUpdate.setPerson(person);
        return mapper.toResponseDto(empRepo.save(empToUpdate));
    }

    public Boolean deleteEmployee(long id) {
        return empRepo.deleteEmployeeById(id) > 0;
    }
}
