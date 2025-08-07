package com.delcons.features.product.controller;

import com.delcons.features.product.service.ProductService;
import com.delcons.features.product.dto.request.ProductCreateDTO;
import com.delcons.features.product.dto.response.ProductResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
@ResponseBody
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getResourceById(@PathVariable("id") long id) {
        return service.getProductById(id);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(Pageable pageable) {
        Page<ProductResponseDTO> productsPage = service.getAllProducts(pageable);

        if (productsPage.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si no hay productos
        }
        return ResponseEntity.ok(productsPage);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody @Valid ProductCreateDTO p) {
        ProductResponseDTO created = service.addProduct(p);
        URI location = URI.create("/products/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

}
