package com.delcons.features.sale.cart.controller;


import com.delcons.features.sale.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart")
class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    public ResponseEntity<String> createCart() {
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<String> addProductToCart(){

        return  ResponseEntity.ok().build();
    }
}
