package com.example.Product_APII.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreatetionResponse {
    private String name;
    private String password;
    private String email;
    private String avatar;
    private LocalDateTime dob;
    private String role;
}
