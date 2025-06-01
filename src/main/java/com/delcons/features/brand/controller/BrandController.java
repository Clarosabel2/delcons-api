package com.delcons.features.brand.controller;

import com.delcons.features.brand.dto.BrandCreateDTO;
import com.delcons.features.brand.dto.BrandResponseDTO;
import com.delcons.features.brand.dto.BrandWithProductsDTO;
import com.delcons.features.brand.mapper.IBrandMapper;
import com.delcons.features.brand.model.Brand;
import com.delcons.features.brand.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/brands")
@ResponseBody
public class BrandController {

    private final BrandService s;
    private final IBrandMapper mapper;

    @Autowired
    public BrandController(BrandService service, IBrandMapper mapper) {
        this.s = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Page<?>> getAllBrands(Pageable pageable, @RequestParam(defaultValue = "false") boolean withProducts) {

        Page<Brand> brands = s.getAllBrands(pageable);
        if (brands.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (withProducts) {
            Page<BrandWithProductsDTO> dtoPage = brands.map(mapper::toBrandWithProductsDTO);
            return ResponseEntity.ok(dtoPage);
        } else {
            Page<BrandResponseDTO> dtoPage = brands.map(mapper::toResponseDTO);
            return ResponseEntity.ok(dtoPage);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable long id) {
        return s.getBrandById(id)
                .map(b ->
                        ResponseEntity.
                                ok(mapper.toResponseDTO(b)))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<BrandResponseDTO> addBrand(@RequestBody @Valid BrandCreateDTO dto) {
        Brand b = mapper.toEntity(dto);
        URI location = URI.create("api/brands/" + b.getId());
        return ResponseEntity
                .created(location)
                .body(mapper.toResponseDTO(s.addBrand(b)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBrand(@PathVariable long id) {
        return ResponseEntity.ok(s.deleteBrandById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateBrand(
            @PathVariable Long id,
            @RequestBody @Valid BrandCreateDTO dto) {

        Brand updatedBrand = s.updateBrand(id, mapper.toEntity(dto));
        BrandResponseDTO response = mapper.toResponseDTO(updatedBrand);
        return ResponseEntity.ok(response);
    }
}
