package com.delcons.features.brand.service;

import com.delcons.features.brand.model.Brand;
import com.delcons.features.brand.repository.BrandRepository;
import com.delcons.features.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository repo;

    public Page<Brand> getAllBrands(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Brand addBrand(Brand b) {
        return repo.save(b);
    }

    public Optional<Brand> getBrandById(long id) {
        return repo.findById(id);
    }

    public boolean deleteBrandById(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public Brand updateBrand(Long id, Brand newData) {
        Brand existingBrand = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand with id " + id + " not found"));

        existingBrand.setName(newData.getName());
        existingBrand.setDescription(newData.getDescription());

        return repo.save(existingBrand);
    }
}
