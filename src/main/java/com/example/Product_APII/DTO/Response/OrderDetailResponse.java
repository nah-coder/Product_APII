package com.example.Product_APII.DTO.Response;

import com.example.Product_APII.Entity.Order;
import com.example.Product_APII.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailResponse {
    private Long id;
    private Double price;
    private Integer quantity;

    private Integer productId;
    private Long orderId;

}