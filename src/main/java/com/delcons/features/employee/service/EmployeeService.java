package com.delcons.features.employee.service;

import com.delcons.features.customer.model.Customer;
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
    public List<Employee> getActivesEmployees() {
        return empRepo.getEmployeesByActive(true);
    }
    public Employee getEmployeeByDni(long dni) {
        return empRepo.findEmployeesByDni(dni);
    }
    public Employee saveEmployee(Employee emp) {
        return empRepo.save(emp);
    }
    public Employee updateEmployee(Employee emp) {
        empRepo.delete(emp);
        return empRepo.save(emp);
    }
    public Boolean deleteEmployee(long id) {
        return empRepo.deleteEmployeeById(id)>0;
    }
}
