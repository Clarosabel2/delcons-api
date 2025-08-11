package com.delcons.features.employee.controller;

import com.delcons.features.employee.dto.EmployeeCreateDTO;
import com.delcons.features.employee.dto.EmployeeResponseDTO;
import com.delcons.features.employee.model.Employee;
import com.delcons.features.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@SecurityRequirement(name = "bearer-key")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Employees", description = "CRUD de empleados, acceso restringido a ADMIN")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService employeeService) {
        this.service = employeeService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los empleados",
            description = "Devuelve todos los empleados registrados en el sistema"
    )
    public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployees(Pageable pageable) {
        return ResponseEntity.ok(service.getAllEmployees(pageable));
    }

    @GetMapping("/search")
    @Operation(
            summary = "Buscar un empleado por email o DNI",
            description = "Devuelve un empleado utilizando como criterio el email o el DNI. Se debe enviar al menos uno de los dos parámetros."
    )
    public ResponseEntity<EmployeeResponseDTO> searchEmployee(
            @Parameter(description = "Email del empleado", example = "juan@example.com")
            @RequestParam(required = false)
            @Email
            String email,
            @Parameter(description = "DNI del empleado (8 dígitos)", example = "38294930")
            @RequestParam(required = false)
            @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
            Long dni) {
        if (email != null) {
            return ResponseEntity.ok(service.getEmployeeByEmail(email));
        } else if (dni != null) {
            return ResponseEntity.ok(service.getEmployeeByDni(dni));
        } else {
            throw new IllegalArgumentException("Debe proporcionar email o dni");
        }
    }

    @GetMapping("/active")
    @Operation(
            summary = "Obtener empleados activos",
            description = "Devuelve una lista paginada de empleados que están marcados como activos."
    )
    public ResponseEntity<Page<EmployeeResponseDTO>> getActivesEmployees(Pageable pageable) {
        return ResponseEntity.ok(service.getEmployeesByActive(pageable, true));
    }

    @GetMapping("/inactive")
    @Operation(
            summary = "Obtener empleados inactivos",
            description = "Devuelve una lista paginada de empleados que están marcados como inactivos."
    )
    public ResponseEntity<Page<EmployeeResponseDTO>> getInactiveEmployees(Pageable pageable) {
        return ResponseEntity.ok(service.getEmployeesByActive(pageable, false));
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo empleado",
            description = "Recibe un objeto con los datos del empleado y lo guarda en la base de datos."
    )
    public ResponseEntity<EmployeeResponseDTO> addEmployee(@RequestBody @Valid EmployeeCreateDTO emp) {
        return ResponseEntity.ok((service.saveEmployee(emp)));
    }

    @DeleteMapping
    @Operation(
            summary = "Eliminar un empleado",
            description = "Elimina un empleado de la base de datos según su ID."
    )
    public ResponseEntity<Boolean> deleteEmployee(@RequestParam long id) {
        return ResponseEntity.ok(service.deleteEmployee(id));
    }

    @PutMapping
    @Operation(
            summary = "Actualizar un empleado existente",
            description = "Actualiza los datos de un empleado utilizando el objeto recibido."
    )
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@RequestBody EmployeeCreateDTO emp) {
        return ResponseEntity.ok(service.updateEmployee(emp));
    }
}
