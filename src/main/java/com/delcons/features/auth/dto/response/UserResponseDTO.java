package com.delcons.features.auth.dto.response;

public record UserResponseDTO(
        String email, String username, String role
) {
}
