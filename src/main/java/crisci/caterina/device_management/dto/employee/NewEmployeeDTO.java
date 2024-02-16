package crisci.caterina.device_management.dto.employee;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewEmployeeDTO(
        @NotEmpty(message = "username is mandatory")
        @Size(min = 6, max = 30, message = "username length must be between 6 and 30 characters")
        String username,
        @NotEmpty(message = "employee name is mandatory")
        @Size(min = 3, max = 30, message = "name length must be between 3 and 30 characters")
        String name,
        @NotEmpty(message = "employee name is mandatory")
        @Size(min = 3, max = 30, message = "name length must be between 3 and 30 characters")
        String surname,
        @Pattern(regexp = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+", message = "provided email has not a valid format")
        String email
) {
}
