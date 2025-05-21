package com.delcons.features.customer.service;

import com.delcons.features.customer.model.Customer;
import com.delcons.features.customer.repository.CustomerRepository;
import com.delcons.features.employee.model.Employee;
import com.delcons.features.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository s;

    public List<Customer> getAllCustomers() {
        return s.findAll();
    }
    public List<Customer> getActivesCustomers() {
        return null;
    }
    public Customer getCustomerByDni(long dni) {
        return s.findByDni(dni);
    }
    public Customer saveCustomer(Customer cus) {
        return s.save(cus);
    }
    public Customer updateCustomer(Customer cus) {
        return null;
    }
    public Boolean deleteCustomer(long id) {
        return null;
    }

}
