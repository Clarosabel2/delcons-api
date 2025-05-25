package com.delcons.features.brand.controller;

import com.delcons.features.brand.dto.BrandCreateDTO;
import com.delcons.features.brand.dto.BrandResponseDTO;
import com.delcons.features.brand.mapper.BrandMapper;
import com.delcons.features.brand.model.Brand;
import com.delcons.features.brand.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/brands")
@ResponseBody
public class BrandController {
    @Autowired
    private BrandService s;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(s.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable long id) {
        return s.getBrandById(id)
                .map(b ->
                        ResponseEntity.
                                ok(BrandMapper.toResponseDTO(b)))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<BrandResponseDTO> addBrand(@RequestBody @Valid BrandCreateDTO dto) {
        Brand b = BrandMapper.toEntity(dto);
        URI location = URI.create("api/brands/" + b.getId());
        return ResponseEntity
                .created(location)
                .body(BrandMapper
                        .toResponseDTO(s.addBrand(b)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBrand(@PathVariable long id) {
        return ResponseEntity.ok(s.deleteBrandById(id));
    }
}
