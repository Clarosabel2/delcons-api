package com.delcons.features.product.service;

import com.delcons.features.brand.model.Brand;
import com.delcons.features.brand.service.BrandService;
import com.delcons.features.product.dto.request.ProductCreateDTO;
import com.delcons.features.product.dto.response.ProductResponseDTO;
import com.delcons.features.product.mapper.IProductMapper;
import com.delcons.features.product.model.Product;
import com.delcons.features.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository repo;
    private final IProductMapper mapper;
    private final BrandService brandService;

    public ProductService(ProductRepository repo, IProductMapper mapper, BrandService brandService) {
        this.repo = repo;
        this.mapper = mapper;
        this.brandService = brandService;
    }

    public ProductResponseDTO addProduct(ProductCreateDTO p) {
        Product newProduct = mapper.toEntity(p);
        Brand brand = brandService.getBrandEntityById(p.brandId());
        newProduct.setBrand(brand);
        Product saved = repo.save(newProduct);
        return mapper.toResponseDTO(saved);
    }

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toResponseDTO);
    }

    public ResponseEntity<ProductResponseDTO> getProductById(long id) {
        return repo.findById(id)
                .map(product -> ResponseEntity.ok(mapper.toResponseDTO(product)))
                .orElse(ResponseEntity.notFound().build());
    }
}
