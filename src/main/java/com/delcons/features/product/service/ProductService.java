package com.delcons.features.product.service;

import com.delcons.features.product.model.Product;
import com.delcons.features.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public Product addProduct(Product p) {
        return repo.save(p);
    }
}
