package com.delcons.features.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductCreateDTO(
        @NotNull Long brandId,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @Positive Double price,
        @NotNull @Min(0) Integer stock
) {}