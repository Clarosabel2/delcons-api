package com.delcons.features.brand.dto.response;

import com.delcons.features.product.dto.response.ProductSummaryResponseDTO;

import java.util.List;

public record BrandWithProductsDTO(
        Long id,
        String name,
        List<ProductSummaryResponseDTO> products
) {
}
