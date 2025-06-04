package com.delcons.features.customer.service;

import com.delcons.features.customer.dto.request.CustomerCreateDTO;
import com.delcons.features.customer.dto.response.CustomerResponseDTO;
import com.delcons.features.customer.mapper.ICustomerMapper;
import com.delcons.features.customer.model.Customer;
import com.delcons.features.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ICustomerMapper mapper;

    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
        Page<CustomerResponseDTO> customers = repository.findAll(pageable).map(mapper::toResponse);
        return customers;
    }

    public List<Customer> getActivesCustomers() {
        return null;
    }

    public CustomerResponseDTO getCustomerByDni(long dni) {
        return mapper.toResponse(repository.findByDni(dni));
    }

    public CustomerResponseDTO saveCustomer(CustomerCreateDTO dto) {
        Customer entity = mapper.toEntity(dto);
        Customer saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    public CustomerResponseDTO updateCustomer(Customer cus) {

        return null;
    }

    public Boolean deleteCustomer(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

}
