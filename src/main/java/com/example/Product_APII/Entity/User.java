package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "activation_expiry")
    private Instant activationExpiry;

    @Lob
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Size(max = 255)
    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "email_code")
    private String emailCode;

    @Column(name = "email_expiry")
    private Instant emailExpiry;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "forgot_password_code")
    private String forgotPasswordCode;

    @Column(name = "forgot_password_expiry")
    private Instant forgotPasswordExpiry;

    @Column(name = "is_active")
    private Boolean isActive;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 512)
    @Column(name = "password", length = 512)
    private String password;

    @Size(max = 255)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 255)
    @Column(name = "purchase_address")
    private String purchaseAddress;

    @Size(max = 255)
    @Column(name = "sex")
    private String sex;

    @Size(max = 255)
    @Column(name = "user_name")
    private String userName;

    @Size(max = 255)
    @Column(name = "refresh_token")
    private String refreshToken;

    @Lob
    @Column(name = "provider")
    private String provider;

    @Size(max = 255)
    @Column(name = "provider_id")
    private String providerId;

    @ManyToMany
    @JoinTable(
            name = "user_role", // Bảng trung gian
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}