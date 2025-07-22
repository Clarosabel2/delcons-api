package com.delcons.features.brand.controller;

import com.delcons.features.brand.dto.request.BrandCreateDTO;
import com.delcons.features.brand.dto.response.BrandResponseDTO;
import com.delcons.features.brand.service.BrandService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/brands")
@ResponseBody
@SecurityRequirement(name = "bearer-key")
public class BrandController {

    private final BrandService s;
    @Autowired
    public BrandController(BrandService service) {
        this.s = service;
    }

    @GetMapping
    public ResponseEntity<Page<?>> getAllBrands(Pageable pageable,
                                                @RequestParam(defaultValue = "false") boolean withProducts) {
        Page<?> result = s.getAllBrands(pageable, withProducts);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable long id) {
        return ResponseEntity.ok(s.getBrandById(id));
    }

    @PostMapping
    public ResponseEntity<BrandResponseDTO> addBrand(@RequestBody @Valid BrandCreateDTO dto) {
        BrandResponseDTO resBrand = s.addBrand(dto);
        URI location = URI.create("api/brands/" + resBrand.id());
        return ResponseEntity
                .created(location)
                .body(resBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateBrand(@PathVariable long id, @RequestBody @Valid BrandCreateDTO dto) {
        BrandResponseDTO brandDto = s.updateBrand(id, dto);
        return ResponseEntity.ok(brandDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable long id) {
        boolean deleted = s.deleteBrandById(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }

}
