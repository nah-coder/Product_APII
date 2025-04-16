package com.example.Product_APII.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReviewRequest {

    private Float deliveryRate;
    private Float shopRate;

    @NotNull
    private Long orderId;  // ID đơn hàng

    @NotNull
    private Long userId;   // ID người dùng
}
