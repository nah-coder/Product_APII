package com.example.Product_APII.DTO.Response;

import com.example.Product_APII.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponse {
    private Long id;
    private String avatar;
    private LocalDate dateOfBirth;
    private String deliveryAddress;
    private String email;
    private String firstName;
    private Boolean isActive;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String sex;
    private String userName;
    private Set<Role> roles;
}
