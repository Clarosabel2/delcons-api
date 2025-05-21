package com.delcons.features.customer.dto;

import jakarta.validation.constraints.*;

public record CustomerCreateDTO(
        @NotNull(message = "DNI no puede ser nulo")
        Long dni,
        @NotBlank @Size(min = 2, max = 50)
        String name,
        @NotBlank @Size(min = 2, max = 50)
        String lastname,
        /*@Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "El email debe tener un formato válido"
        )*/
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
        String phone,
        @NotBlank @Size(max = 100)
        String address
) {
}
