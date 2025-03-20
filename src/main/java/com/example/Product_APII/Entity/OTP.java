package com.example.Product_APII.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OTP {
    private String otpCode;
    private String email;
    private LocalDateTime expiryTime;
    private int attempts;
}
