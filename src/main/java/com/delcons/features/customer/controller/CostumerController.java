package com.delcons.features.customer.controller;

import com.delcons.features.customer.dto.CustomerCreateDTO;
import com.delcons.features.customer.dto.CustomerResponseDTO;
import com.delcons.features.customer.mapper.CustomerMapper;
import com.delcons.features.customer.model.Customer;
import com.delcons.features.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/customers")
public class CostumerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCostumers() {
        List<CustomerResponseDTO> customerList = customerService
                .getAllCustomers().stream().map(CustomerMapper::toResponseDTO).toList();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByDNI(@PathVariable int dni) {
        CustomerResponseDTO cus = CustomerMapper.toResponseDTO(customerService.getCustomerByDni(dni));
        return ResponseEntity.ok(cus);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid CustomerCreateDTO newCustomer) {
        return ResponseEntity.ok(customerService.saveCustomer(CustomerMapper.toEntity(newCustomer)));
    }

}
