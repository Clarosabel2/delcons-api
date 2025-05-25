package com.delcons.features.employee.controller;

import com.delcons.features.employee.dto.EmployeeCreateDTO;
import com.delcons.features.employee.dto.EmployeeResponseDTO;
import com.delcons.features.employee.mapper.EmployeeMapper;
import com.delcons.features.employee.model.Employee;
import com.delcons.features.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@ResponseBody
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<Iterable<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees().stream().map(EmployeeMapper::toResponseDTO).toList());
    }
    @GetMapping("/{dni}")
    public ResponseEntity<Employee> getEmployeeByDNI(@PathVariable long dni) {
        return ResponseEntity.ok(service.getEmployeeByDni(dni));
    }
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO>  addEmployee(@RequestBody @Valid EmployeeCreateDTO emp) {
        return ResponseEntity.ok(EmployeeMapper.toResponseDTO(service.saveEmployee(emp)));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteEmployee(@RequestParam long id) {
        return ResponseEntity.ok(service.deleteEmployee(id));
    }
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) {
        return ResponseEntity.ok(service.updateEmployee(emp));
    }
    @GetMapping("/actives")
    public ResponseEntity<Iterable<EmployeeResponseDTO>> getActivesEmployees() {
        return ResponseEntity.ok(service.getEmployeesByActive(true)
                .stream()
                .map(EmployeeMapper::toResponseDTO)
                .toList());
    }
    @GetMapping("/inactive")
    public ResponseEntity<Iterable<EmployeeResponseDTO>> getInactiveEmployees() {
        return ResponseEntity.ok(service.getEmployeesByActive(false)
                .stream()
                .map(EmployeeMapper::toResponseDTO)
                .toList());
    }

}
