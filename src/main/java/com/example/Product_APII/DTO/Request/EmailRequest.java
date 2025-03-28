package com.example.Product_APII.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailRequest {
    @NotEmpty(message = "EmailBLANK")
    @Email(message = "INVALID_EMAIL")
    private String email;
}
