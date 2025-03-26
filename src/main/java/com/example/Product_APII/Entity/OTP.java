package com.example.Product_APII.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OTP implements Serializable {
    private static final long serialVersionUID = 1L;
    private String otpCode;
    private String email;
    private int attempts;
}
