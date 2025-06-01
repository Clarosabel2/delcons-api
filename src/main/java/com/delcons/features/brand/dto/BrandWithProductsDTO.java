package com.delcons.features.brand.dto;

import com.delcons.features.product.dto.ProductResponseDTO;

import java.util.List;

public record BrandWithProductsDTO(
        Long id,
        String name,
        List<ProductResponseDTO> products
) {
}
