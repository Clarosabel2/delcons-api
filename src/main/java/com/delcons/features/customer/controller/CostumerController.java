package com.delcons.features.customer.controller;

import com.delcons.features.customer.dto.CustomerCreateDTO;
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
    public ResponseEntity<List<Customer>> getCostumers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Customer> getCustomerByDNI(@PathVariable int dni) {
        Customer cus = customerService.getCustomerByDni(dni);
        return ResponseEntity.ok(cus);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid CustomerCreateDTO newCustomer) {

        return ResponseEntity.ok(customerService.saveCustomer(CustomerMapper.toEntity(newCustomer)));
    }

}
