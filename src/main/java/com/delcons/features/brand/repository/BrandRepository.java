package com.delcons.features.brand.repository;

import com.delcons.features.brand.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository <Brand, Long>{
    Page<Brand> findAll(Pageable pageable);
}
