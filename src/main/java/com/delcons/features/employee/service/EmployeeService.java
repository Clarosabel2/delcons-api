package com.delcons.features.employee.service;

import com.delcons.features.customer.model.Customer;
import com.delcons.features.employee.dto.EmployeeCreateDTO;
import com.delcons.features.employee.mapper.EmployeeMapper;
import com.delcons.features.employee.model.Employee;
import com.delcons.features.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    public List<Employee> getAllEmployees() {

        return empRepo.findAll();
    }
    public List<Employee> getEmployeesByActive(boolean active) {

        return empRepo.getEmployeesByActive(active);
    }
    public Employee getEmployeeByDni(long dni) {

        return empRepo.findEmployeesByDni(dni);
    }

    public Employee saveEmployee(EmployeeCreateDTO emp) {
        Employee newEmp = EmployeeMapper.toEntity(emp);
        return empRepo.save(newEmp);
    }
    public Employee updateEmployee(Employee emp) {
        empRepo.delete(emp);
        return empRepo.save(emp);
    }
    public Boolean deleteEmployee(long id) {

        return empRepo.deleteEmployeeById(id)>0;
    }
}
