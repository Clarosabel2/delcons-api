package com.delcons.features.employee.model;

import com.delcons.features.user.model.User;
import com.delcons.shared.models.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends Person {
    @Getter @Setter
    private Boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

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
