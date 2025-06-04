package com.delcons.features.customer.controller;

import com.delcons.features.customer.dto.request.CustomerCreateDTO;
import com.delcons.features.customer.dto.response.CustomerResponseDTO;
import com.delcons.features.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@ResponseBody
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(Pageable pageable) {
        Page<CustomerResponseDTO> result = customerService.getAllCustomers(pageable);
        if (result == null) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByDNI(@PathVariable int dni) {
        CustomerResponseDTO cus = customerService.getCustomerByDni(dni);
        return ResponseEntity.ok(cus);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> addCustomer(@RequestBody @Valid CustomerCreateDTO newCustomer) {
        CustomerResponseDTO customerResponseDTO = customerService.saveCustomer(newCustomer);
        URI location = URI.create("/customers/" + newCustomer.dni());
        return ResponseEntity.created(location).body(customerResponseDTO);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long dni) {
        if(customerService.deleteCustomer(dni)){
            return ResponseEntity.noContent().build() ;
        }
        else{
            return ResponseEntity.notFound().build() ;
        }
    }
}
