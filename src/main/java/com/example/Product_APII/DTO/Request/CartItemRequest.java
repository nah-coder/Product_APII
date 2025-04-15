package com.example.Product_APII.DTO.Request;


import lombok.Data;

@Data
public class CartItemRequest {
    private int productId;
    private Integer quantity;
}
