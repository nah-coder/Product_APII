package com.example.Product_APII.DTO.Request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequest {

    @NotNull
    private int productId;

    @NotNull
    private Long orderReviewId;

    private String content;

    private String image;

    private String video;

    @Min(0)
    @Max(5)
    private Float rate;
}
