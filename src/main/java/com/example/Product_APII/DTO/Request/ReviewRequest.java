package com.example.Product_APII.DTO.Request;


import lombok.Data;

@Data
public class ReviewRequest {
    private int userId;
    private int orderDetailId;
    private String comment;
    private double rating;
}