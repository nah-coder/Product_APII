package com.example.Product_APII.DTO.Response;


import lombok.Data;

@Data
public class CartItemResponse {
    private int id;
    private String productName;
    private Integer quantity;
    private Double productPrice;
    private int userId;
}
