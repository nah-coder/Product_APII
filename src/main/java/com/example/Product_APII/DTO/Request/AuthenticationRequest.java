package com.example.Product_APII.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationRequest {
    @NotBlank(message = "EmailBLANK")
    @Email(message = "INVALID_EMAIL")
    private String email;
    @NotBlank(message = "PassBLANK")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "INVALID_PASSWORD")
    private String password;
}
