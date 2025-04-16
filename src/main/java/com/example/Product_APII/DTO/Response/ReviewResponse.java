package com.example.Product_APII.DTO.Response;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
public class ReviewResponse {

    private Long id;

    private String content;

    private String image;

    private String video;

    private Float rate;

    private Instant date;

    private Boolean isHide;

    private Long productId;

    private Long userId;

    private Long orderReviewId;
}

