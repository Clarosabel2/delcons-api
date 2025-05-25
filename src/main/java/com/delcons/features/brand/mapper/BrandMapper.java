package com.delcons.features.brand.mapper;

import com.delcons.features.brand.dto.BrandCreateDTO;
import com.delcons.features.brand.dto.BrandResponseDTO;
import com.delcons.features.brand.model.Brand;

public class BrandMapper {
    public static BrandResponseDTO toResponseDTO(Brand b) {
        return new BrandResponseDTO(
                b.getId(),
                b.getName(),
                b.getDescription());
    }

    public static Brand toEntity(BrandCreateDTO dto) {
        return new Brand(
                0,
                dto.name(),
                dto.description());
    }
}
