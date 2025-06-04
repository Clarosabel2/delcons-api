package com.delcons.features.product.dto.response;

public record ProductSummaryResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock
) {
}
