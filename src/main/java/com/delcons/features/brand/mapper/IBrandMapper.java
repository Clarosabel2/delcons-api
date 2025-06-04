package com.delcons.features.brand.mapper;

import com.delcons.features.brand.dto.request.BrandCreateDTO;
import com.delcons.features.brand.dto.response.BrandResponseDTO;
import com.delcons.features.brand.dto.response.BrandWithProductsDTO;
import com.delcons.features.brand.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandMapper {
    Brand toEntity(BrandCreateDTO dto);
    BrandResponseDTO toResponseDTO(Brand brand);
    BrandWithProductsDTO toBrandWithProductsDTO(Brand brand);
}
