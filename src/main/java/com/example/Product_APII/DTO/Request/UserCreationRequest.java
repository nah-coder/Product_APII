package com.example.Product_APII.DTO.Request;

import com.example.Product_APII.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    @NotEmpty(message = "NameBLANK")
    @Size(min = 8, message = "USERNAME_INVALID")
    private String userName;
    @NotEmpty(message = "PassBLANK")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "INVALID_PASSWORD")
    private String password;
    @NotEmpty(message = "EmailBLANK")
    @Email(message = "INVALID_EMAIL")
    private String email;
    private String avatar;
    private String phoneNumber;
    private Set<Role> roles ;
}
