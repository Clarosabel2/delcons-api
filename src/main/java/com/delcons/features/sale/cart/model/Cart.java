package com.delcons.features.sale.cart.model;

import com.delcons.features.customer.model.Customer;
import com.delcons.features.sale.common.CartStatus;
import com.delcons.shared.models.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "carts")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CartStatus status = CartStatus.ACTIVE;

    private Double total = 0.0;
    private int quantityItems = 0;

    private void calculateTotal() {
        items.forEach(cartItem -> {
            total += cartItem.getSubtotal();
        });
        quantityItems = items.size();
    }

    public void addItem(CartItem cartItem) {
        items.add(cartItem);
        calculateTotal();
    }
    public void removeItem(Long idProduct) {
        items.removeIf(item-> item.getProduct().getId() == idProduct);
        calculateTotal();
    }
}
