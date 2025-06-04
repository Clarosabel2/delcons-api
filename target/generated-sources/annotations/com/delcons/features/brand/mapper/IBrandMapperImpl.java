package com.delcons.features.brand.mapper;

import com.delcons.features.brand.dto.request.BrandCreateDTO;
import com.delcons.features.brand.dto.response.BrandResponseDTO;
import com.delcons.features.brand.dto.response.BrandWithProductsDTO;
import com.delcons.features.brand.model.Brand;
import com.delcons.features.product.dto.response.ProductSummaryResponseDTO;
import com.delcons.features.product.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T16:08:48-0300",
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
        List<ProductSummaryResponseDTO> products = null;

        id = brand.getId();
        name = brand.getName();
        products = productListToProductSummaryResponseDTOList( brand.getProducts() );

        BrandWithProductsDTO brandWithProductsDTO = new BrandWithProductsDTO( id, name, products );

        return brandWithProductsDTO;
    }

    protected ProductSummaryResponseDTO productToProductSummaryResponseDTO(Product product) {
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

        ProductSummaryResponseDTO productSummaryResponseDTO = new ProductSummaryResponseDTO( id, name, description, price, stock );

        return productSummaryResponseDTO;
    }

    protected List<ProductSummaryResponseDTO> productListToProductSummaryResponseDTOList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductSummaryResponseDTO> list1 = new ArrayList<ProductSummaryResponseDTO>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductSummaryResponseDTO( product ) );
        }

        return list1;
    }
}
