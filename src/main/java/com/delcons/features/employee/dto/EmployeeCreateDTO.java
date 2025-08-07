package com.delcons.features.employee.dto;

import com.delcons.features.employee.enums.BusinessArea;
import com.delcons.shared.dtos.AddressRequestDTO;
import jakarta.validation.constraints.*;

public record EmployeeCreateDTO(
        @NotNull @Min(99999999) Long dni,
        @NotBlank String name,
        @NotBlank String lastname,
        @Email @NotBlank String email,
        @Pattern(regexp = "^\\+\\d{1,3}\\d{9}$") String phone,
        @NotNull BusinessArea area,
        @NotNull AddressRequestDTO address
) {

}
