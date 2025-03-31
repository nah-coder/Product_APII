package com.example.Product_APII.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetAllRequest {
    private String id;
    @NotEmpty(message = "NameBLANK")
    @Size(min = 8, message = "USERNAME_INVALID")
    private String name;
    @NotEmpty(message = "PassBLANK")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "INVALID_PASSWORD")
    private String password;
    @NotEmpty(message = "EmailBLANK")
    @Email(message = "INVALID_EMAIL")
    private String email;
    private String avatar;
    private LocalDateTime dob;
}
