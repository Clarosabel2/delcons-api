package com.delcons.features.auth.controller;

import com.delcons.features.auth.dto.LoginRequest;
import com.delcons.features.auth.dto.RegisterRequest;
import com.delcons.features.auth.dto.TokenDTO;
import com.delcons.features.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("register")
    public ResponseEntity<TokenDTO> register(@RequestBody @Valid RegisterRequest request) {
        TokenDTO token = authService.register(request);
        return ResponseEntity.ok(token);
    }
}
