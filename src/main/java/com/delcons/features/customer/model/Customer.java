package com.delcons.features.customer.model;

import com.delcons.common.models.Person;
import com.delcons.features.sale.model.invoice.Invoice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "customers")
public class Customer extends Person {
    @Getter @Setter
    private String level;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;

    public Customer(Long dni, String name, String lastname, String email, String phone, String address, String level) {
        super(dni, name, lastname, email, phone, address);
        this.level = level;
    }

}
