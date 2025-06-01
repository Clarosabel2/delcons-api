package com.delcons.features.brand.mapper;

import com.delcons.features.brand.dto.BrandCreateDTO;
import com.delcons.features.brand.dto.BrandResponseDTO;
import com.delcons.features.brand.dto.BrandWithProductsDTO;
import com.delcons.features.brand.model.Brand;
import com.delcons.features.product.dto.ProductResponseDTO;
import com.delcons.features.product.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T23:38:31-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class IBrandMapperImpl implements IBrandMapper {

    @Override
    public Brand toEntity(BrandCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Brand.BrandBuilder brand = Brand.builder();

        brand.name( dto.name() );
        brand.description( dto.description() );

        return brand.build();
    }

    @Override
    public BrandResponseDTO toResponseDTO(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = brand.getId();
        name = brand.getName();
        description = brand.getDescription();

        BrandResponseDTO brandResponseDTO = new BrandResponseDTO( id, name, description );

        return brandResponseDTO;
    }

    @Override
    public BrandWithProductsDTO toBrandWithProductsDTO(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        List<ProductResponseDTO> products = null;

        id = brand.getId();
        name = brand.getName();
        products = productListToProductResponseDTOList( brand.getProducts() );

        BrandWithProductsDTO brandWithProductsDTO = new BrandWithProductsDTO( id, name, products );

        return brandWithProductsDTO;
    }

    @Override
    public List<BrandWithProductsDTO> toBrandWithProductsDTO(List<Brand> brands) {
        if ( brands == null ) {
            return null;
        }

        List<BrandWithProductsDTO> list = new ArrayList<BrandWithProductsDTO>( brands.size() );
        for ( Brand brand : brands ) {
            list.add( toBrandWithProductsDTO( brand ) );
        }

        return list;
    }

    protected ProductResponseDTO productToProductResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        Double price = null;
        Integer stock = null;

        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        stock = product.getStock();

        String brandName = null;

        ProductResponseDTO productResponseDTO = new ProductResponseDTO( id, name, description, price, stock, brandName );

        return productResponseDTO;
    }

    protected List<ProductResponseDTO> productListToProductResponseDTOList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductResponseDTO> list1 = new ArrayList<ProductResponseDTO>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductResponseDTO( product ) );
        }

        return list1;
    }
}
