package com.delcons.features.customer.model;

import com.delcons.common.models.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "customers")
public class Customer extends Person {
    @Getter @Setter
    private String level;

    public Customer(Long dni, String name, String lastname, String email, String phone, String address, String level) {
        super(dni, name, lastname, email, phone, address);
        this.level = level;
    }

}
