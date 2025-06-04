package com.delcons.features.product.dto.response;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        String brandName
) {}
