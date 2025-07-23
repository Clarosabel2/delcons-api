package com.delcons.features.auth.dto.request;

import com.delcons.features.user.model.UserRole;
import jakarta.validation.constraints.*;

public record UserCreateWithRoleDTO(
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
        String confirmPassword,
        @NotNull
        UserRole role
) {
    public UserCreateDTO toUserCreateDTO(){
        return new UserCreateDTO(email,username,password,confirmPassword);
    }
}
