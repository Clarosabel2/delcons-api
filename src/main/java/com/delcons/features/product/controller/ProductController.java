package com.delcons.features.product.controller;

import com.delcons.features.product.dto.ProductCreateDTO;
import com.delcons.features.product.dto.ProductResponseDTO;
import com.delcons.features.product.mapper.IProductMapper;
import com.delcons.features.product.model.Product;
import com.delcons.features.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@ResponseBody
public class ProductController {

    private final ProductService s;
    private final IProductMapper mapper;

    public ProductController(ProductService s, IProductMapper mapper) {
        this.s = s;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody @Valid ProductCreateDTO p) {
        Product newProduct = mapper.toEntity(p);
        return ResponseEntity.ok(mapper.toResponseDTO(s.addProduct(newProduct)));
    }

}
