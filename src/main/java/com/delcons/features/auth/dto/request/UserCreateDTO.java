package com.delcons.features.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(
        @Email @NotBlank
        String email,
        @NotBlank
        String username,
        @NotBlank
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Pattern(
                regexp = ".*[!@#$%^&*()_+\\-={}:;\"'<>,.?/\\\\|\\[\\]].*",
                message = "Password must contain at least one symbol"
        )
        String password,
        @NotBlank
        String confirmPassword
) {


}
