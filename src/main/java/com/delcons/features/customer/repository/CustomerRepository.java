package com.delcons.features.customer.repository;

import com.delcons.features.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByDni(Long dni);
    Page<Customer> findAll(Pageable pageable);
}
