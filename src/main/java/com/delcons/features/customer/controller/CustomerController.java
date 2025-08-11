package com.delcons.features.customer.controller;

import com.delcons.features.customer.dto.request.CustomerCreateDTO;
import com.delcons.features.customer.dto.response.CustomerResponseDTO;
import com.delcons.features.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/customers")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Customers", description = "Gestión de clientes: alta, baja, consulta y listado")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes paginados",
            description = "Devuelve una página con todos los clientes.")
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(Pageable pageable) {
        Page<CustomerResponseDTO> result = customerService.getAllCustomers(pageable);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{dni}")
    @Operation(summary = "Obtener cliente por DNI",
            description = "Devuelve un cliente cuyo DNI coincide con el parámetro.")
    public ResponseEntity<CustomerResponseDTO> getCustomerByDNI(@PathVariable int dni) {
        CustomerResponseDTO cus = customerService.getCustomerByDni(dni);
        return ResponseEntity.ok(cus);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo cliente",
            description = "Crea un cliente nuevo a partir de los datos enviados.")
    public ResponseEntity<CustomerResponseDTO> addCustomer(@RequestBody @Valid CustomerCreateDTO newCustomer) {
        CustomerResponseDTO customerResponseDTO = customerService.saveCustomer(newCustomer);
        URI location = URI.create("/customers/" + newCustomer.dni());
        return ResponseEntity.created(location).body(customerResponseDTO);
    }



    @DeleteMapping("/{dni}")
    @Operation(summary = "Eliminar un cliente",
            description = "Elimina un cliente identificado por su DNI.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Void> deleteCustomer(@PathVariable long dni) {
        if(customerService.deleteCustomer(dni)){
            return ResponseEntity.noContent().build() ;
        }
        else{
            return ResponseEntity.notFound().build() ;
        }
    }
}
