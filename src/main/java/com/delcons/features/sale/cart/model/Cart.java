package com.delcons.features.sale.cart.model;

import com.delcons.features.customer.model.Customer;
import com.delcons.features.sale.common.CartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private LocalDateTime createAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private CartStatus status = CartStatus.ACTIVE;
    private Double total = 0.0;
}
