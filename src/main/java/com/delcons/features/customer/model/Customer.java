package com.delcons.features.customer.model;

import com.delcons.features.customer.enums.CustomerLevel;
import com.delcons.shared.models.Person;
import com.delcons.features.sale.invoice.model.Invoice;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "customers")
@Data
public class Customer {
    @Id
    private Long id;

    @OneToOne @MapsId @JoinColumn(name = "id")
    private Person person;

    @Enumerated(EnumType.STRING) @Column(name = "level")
    private CustomerLevel customerLevel;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Invoice> invoices;
}
