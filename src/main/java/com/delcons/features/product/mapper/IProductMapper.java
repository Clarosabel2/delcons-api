package com.delcons.features.product.mapper;

import com.delcons.features.brand.mapper.IBrandMapper;
import com.delcons.features.product.dto.request.ProductCreateDTO;
import com.delcons.features.product.dto.response.ProductResponseDTO;
import com.delcons.features.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IBrandMapper.class})
public interface IProductMapper {
    Product toEntity(ProductCreateDTO newProduct);
    @Mapping(source = "brand.name", target = "brandName")
    ProductResponseDTO toResponseDTO(Product product);
}
