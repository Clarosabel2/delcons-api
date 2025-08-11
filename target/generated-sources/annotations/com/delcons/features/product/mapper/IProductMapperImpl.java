package com.delcons.features.product.mapper;

import com.delcons.features.brand.model.Brand;
import com.delcons.features.product.dto.request.ProductCreateDTO;
import com.delcons.features.product.dto.response.ProductResponseDTO;
import com.delcons.features.product.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-11T16:11:01-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class IProductMapperImpl implements IProductMapper {

    @Override
    public Product toEntity(ProductCreateDTO newProduct) {
        if ( newProduct == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( newProduct.name() );
        product.setDescription( newProduct.description() );
        if ( newProduct.price() != null ) {
            product.setPrice( newProduct.price() );
        }
        if ( newProduct.stock() != null ) {
            product.setStock( newProduct.stock() );
        }

        return product;
    }

    @Override
    public ProductResponseDTO toResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        String brandName = null;
        Long id = null;
        String name = null;
        String description = null;
        Double price = null;
        Integer stock = null;

        brandName = productBrandName( product );
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        stock = product.getStock();

        ProductResponseDTO productResponseDTO = new ProductResponseDTO( id, name, description, price, stock, brandName );

        return productResponseDTO;
    }

    private String productBrandName(Product product) {
        Brand brand = product.getBrand();
        if ( brand == null ) {
            return null;
        }
        return brand.getName();
    }
}
