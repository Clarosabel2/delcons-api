package com.delcons.features.employee.model;

import com.delcons.features.employee.enums.BusinessArea;
import com.delcons.shared.models.Person;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Data
public class Employee {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Person person;
    @Enumerated(EnumType.STRING)
    private BusinessArea area;
}
