package com.delcons.features.auth.controller;

import com.delcons.features.auth.dto.request.LoginRequest;
import com.delcons.features.auth.dto.request.UserCreateDTO;
import com.delcons.features.auth.dto.request.UserCreateWithRoleDTO;
import com.delcons.features.auth.dto.response.TokenDTO;
import com.delcons.features.auth.dto.response.UserResponseDTO;
import com.delcons.features.auth.service.AuthService;
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
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticationUser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody @Valid UserCreateDTO request) {
        TokenDTO token = authService.registerUser(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/admin/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> registerUserByAdmin(
            @RequestBody @Valid UserCreateWithRoleDTO request) {
        return ResponseEntity.ok(authService.registerUserByAdmin(request));
    }
}
