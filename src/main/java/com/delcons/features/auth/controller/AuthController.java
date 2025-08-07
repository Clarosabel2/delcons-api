package com.delcons.features.auth.controller;

import com.delcons.features.auth.dto.request.LoginRequest;
import com.delcons.features.auth.dto.request.UserCreateDTO;
import com.delcons.features.auth.dto.request.UserCreateWithRoleDTO;
import com.delcons.features.auth.dto.response.TokenDTO;
import com.delcons.features.auth.dto.response.UserResponseDTO;
import com.delcons.features.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints para login, registro y autenticación de usuarios")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario autenticarse y obtener un token JWT")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticationUser(loginRequest));
    }

    @PostMapping("/register")
    @Operation(summary = "Registro de usuario", description = "Registra un nuevo usuario y devuelve su token JWT")
    public ResponseEntity<TokenDTO> register(@RequestBody @Valid UserCreateDTO request) {
        TokenDTO token = authService.registerUser(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/admin/register")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Registro por admin", description = "Permite a un administrador registrar un nuevo usuario con rol específico")
    public ResponseEntity<UserResponseDTO> registerUserByAdmin(
            @RequestBody @Valid UserCreateWithRoleDTO request) {
        return ResponseEntity.ok(authService.registerUserByAdmin(request));
    }
}
