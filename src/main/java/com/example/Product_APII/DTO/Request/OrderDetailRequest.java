package com.example.Product_APII.DTO.Request;

import com.example.Product_APII.Entity.Order;
import com.example.Product_APII.Entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailRequest {
    private Long id;
    private Double price;
    private Integer quantity;

    private Integer productId;
    private Long orderId;
}