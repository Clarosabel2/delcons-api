package com.delcons.features.brand.service;

import com.delcons.features.brand.model.Brand;
import com.delcons.features.brand.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private BrandRepository repo;

    public List<Brand> getAllBrands() {
        return repo.findAll();
    }

    public Brand addBrand(Brand b) {
        return repo.save(b);
    }

    public Optional<Brand>  getBrandById(long id) {
        return repo.findById(id);
    }

    public boolean deleteBrandById(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
