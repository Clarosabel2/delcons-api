package com.delcons.features.sale.cart.service;

import com.delcons.features.customer.model.Customer;
import com.delcons.features.customer.repository.CustomerRepository;
import com.delcons.features.product.model.Product;
import com.delcons.features.product.repository.ProductRepository;
import com.delcons.features.product.service.ProductService;
import com.delcons.features.sale.cart.model.Cart;
import com.delcons.features.sale.cart.model.CartItem;
import com.delcons.features.sale.cart.repository.CartRedisRepository;
import com.delcons.features.sale.cart.repository.CartRepository;
import jakarta.persistence.Convert;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartRedisRepository cartRedisRepository;
    private final CustomerRepository customerRepository;

    public CartService(ProductRepository productRepository,
                       CartRepository cartRepository,
                       CartRedisRepository cartRedisRepository,
                       CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartRedisRepository = cartRedisRepository;
        this.customerRepository = customerRepository;
    }

    public String createCart(String clientId) {
        Cart newCart = new Cart();
        Optional<Customer> customer = customerRepository.findById(Long.valueOf(clientId));
        if (customer.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        newCart.setCustomer(customer.get());
        var cart = cartRepository.save(newCart);
        cartRedisRepository.saveCart(clientId, cart);
        return cart.toString();
    }

    public void addProductToCart(Long productId, int quantity) {
        Cart cart = cartRedisRepository.getCart("1");
        Optional<Product> product = this.productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        cart.addItem(new CartItem(product.get(), quantity));
        cartRepository.save(cart);
    }

    public void removeProductFromCart(Long productId) {
        Cart cart = cartRedisRepository.getCart("1");
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

}
