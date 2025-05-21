package com.delcons.features.employee.controller;

import com.delcons.features.employee.dto.EmployeeCreateDTO;
import com.delcons.features.employee.model.Employee;
import com.delcons.features.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee>  addEmployee(@RequestBody @Valid EmployeeCreateDTO emp) {

        return ResponseEntity.ok(employeeService.saveEmployee(
                new Employee(
                        emp.dni(),
                        emp.name(),
                        emp.lastname(),
                        emp.email(),
                        emp.phone(),
                        emp.address())));
    }


}
