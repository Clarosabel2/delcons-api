package com.delcons.features.brand.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BrandCreateDTO(
        @NotBlank String name,
        String description
) {}

