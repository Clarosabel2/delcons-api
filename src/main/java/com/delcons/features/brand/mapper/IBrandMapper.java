package com.delcons.features.brand.mapper;

import com.delcons.features.brand.dto.BrandCreateDTO;
import com.delcons.features.brand.dto.BrandResponseDTO;
import com.delcons.features.brand.dto.BrandWithProductsDTO;
import com.delcons.features.brand.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandMapper {
    Brand toEntity(BrandCreateDTO dto);
    BrandResponseDTO toResponseDTO(Brand brand);
    BrandWithProductsDTO toBrandWithProductsDTO(Brand brand);
    List<BrandWithProductsDTO>toBrandWithProductsDTO(List<Brand> brands);
}
