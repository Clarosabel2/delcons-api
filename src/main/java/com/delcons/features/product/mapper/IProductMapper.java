package com.delcons.features.product.mapper;

import com.delcons.features.product.dto.ProductCreateDTO;
import com.delcons.features.product.dto.ProductResponseDTO;
import com.delcons.features.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    Product toEntity(ProductCreateDTO newProduct);
    ProductResponseDTO toResponseDTO(Product product);
}
