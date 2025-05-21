package com.delcons.features.employee.model;

import com.delcons.common.models.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends Person {
    private Boolean active;

    public Employee(Long dni, String name, String lastname, String email, String phone, String address) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.active = true;
    }
}
