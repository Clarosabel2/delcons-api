package com.delcons.features.brand.controller;

import com.delcons.features.brand.dto.request.BrandCreateDTO;
import com.delcons.features.brand.dto.response.BrandResponseDTO;
import com.delcons.features.brand.dto.response.BrandWithProductsDTO;
import com.delcons.features.brand.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/brands")
@ResponseBody
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Brands", description = "Endpoints CRUD to manage brands")
public class BrandController {

    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las marcas",
            description = "Devuelve una lista paginada de marcas."
    )
    public ResponseEntity<Page<BrandResponseDTO>> getAllBrands(Pageable pageable) {
        Page<BrandResponseDTO> result = service.getAllBrands(pageable);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/with-products")
    @Operation(
            summary = "Obtener todas las marcas con sus productos",
            description = "Devuelve una lista paginada de marcas, incluyendo sus productos asociados."
    )
    public ResponseEntity<Page<BrandWithProductsDTO>> getAllBrandsWithProducts(Pageable pageable) {
        Page<BrandWithProductsDTO> result = service.getAllBrandsWithProducts(pageable);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener una marca por ID",
            description = "Devuelve los datos de una marca específica según su identificador único."
    )
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable long id) {
        return ResponseEntity.ok(service.getBrandById(id));
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva marca",
            description = "Crea una nueva marca a partir del cuerpo de la solicitud. Devuelve la marca creada con su ID."
    )
    public ResponseEntity<BrandResponseDTO> addBrand(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear una nueva marca",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BrandCreateDTO.class))
            )
            @RequestBody @Valid BrandCreateDTO dto) {
        BrandResponseDTO resBrand = service.addBrand(dto);
        URI location = URI.create("api/brands/" + resBrand.id());
        return ResponseEntity
                .created(location)
                .body(resBrand);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una marca existente",
            description = "Actualiza los datos de una marca existente usando su ID."
    )
    public ResponseEntity<BrandResponseDTO> updateBrand(
            @Parameter(description = "ID de la marca a actualizar", example = "1")
            @PathVariable long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados de la marca",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BrandCreateDTO.class))
            )
            @RequestBody @Valid BrandCreateDTO dto) {
        BrandResponseDTO brandDto = service.updateBrand(id, dto);
        return ResponseEntity.ok(brandDto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una marca",
            description = "Elimina una marca según su ID. Devuelve 204 si fue eliminada, o 404 si no se encontró."
    )
    public ResponseEntity<Void> deleteBrand( @Parameter(description = "ID de la marca a eliminar", example = "1") @PathVariable long id) {
        boolean deleted = service.deleteBrandById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
