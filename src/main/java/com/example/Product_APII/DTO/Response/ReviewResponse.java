package com.example.Product_APII.DTO.Response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewResponse {
    private int id;
    private int userId;
    private int bookId;
    private int orderDetailId;
    private String comment;
    private double rating;
    private Byte status;
    private String reply;
    private LocalDateTime createdAt;
}
