package com.delcons.features.brand.service;

import com.delcons.features.brand.dto.request.BrandCreateDTO;
import com.delcons.features.brand.dto.response.BrandResponseDTO;
import com.delcons.features.brand.mapper.IBrandMapper;
import com.delcons.features.brand.model.Brand;
import com.delcons.features.brand.repository.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandRepository repo;
    @Autowired
    private IBrandMapper mapper;

    public Page<?> getAllBrands(Pageable pageable, boolean withProducts) {
        Page<Brand> brands = repo.findAll(pageable);

        if (brands.isEmpty()) {
            return Page.empty();
        }

        if (withProducts) {
            return brands.map(mapper::toBrandWithProductsDTO);
        } else {
            return brands.map(mapper::toResponseDTO);
        }
    }

    public BrandResponseDTO addBrand(BrandCreateDTO b) {
        Brand newBrand = mapper.toEntity(b);
        return mapper.toResponseDTO(repo.save(newBrand));
    }

    public BrandResponseDTO getBrandById(long id) {
        Brand brand = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with ID: " + id));
        return mapper.toResponseDTO(brand);
    }

    public Brand getBrandEntityById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with ID: " + id));
    }

    public boolean deleteBrandById(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public BrandResponseDTO updateBrand(Long id, BrandCreateDTO updateBrand) {

        Brand existingBrand = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand with id " + id + " not found"));

        existingBrand.setName(updateBrand.name());
        existingBrand.setDescription(updateBrand.description());
        return mapper.toResponseDTO(repo.save(existingBrand));
    }
}
