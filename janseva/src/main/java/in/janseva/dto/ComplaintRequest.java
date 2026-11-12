package in.janseva.dto;

import in.janseva.model.Department;
import jakarta.validation.constraints.*;

public record ComplaintRequest(
    @NotBlank @Size(max=100) String citizenName,
    @NotBlank @Email @Size(max=120) String email,
    @NotBlank @Pattern(regexp="[0-9+() -]{8,20}") String phone,
    @NotNull Department department,
    @NotBlank @Size(max=150) String subject,
    @NotBlank @Size(min=10,max=2000) String description,
    @NotBlank @Size(max=300) String location
) {}
