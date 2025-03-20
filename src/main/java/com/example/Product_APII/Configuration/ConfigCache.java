package com.example.Product_APII.Configuration;

import com.example.Product_APII.Entity.OTP;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ConfigCache {
    @Bean
    public Cache<String, OTP> otpCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)  // Cache hết hạn sau 5 phút
                .maximumSize(1000)  // Giới hạn số lượng tối đa là 1000
                .build();
    }
    @Bean
    public Cache<String, String> resetPasswordCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)  // Cache hết hạn sau 5 phút
                .maximumSize(1000)  // Giới hạn số lượng tối đa là 1000
                .build();
    }
}