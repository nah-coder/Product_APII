package com.example.Product_APII.DTO.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReviewResponse {

    private Long id;
    private Float deliveryRate;
    private Float shopRate;
    private Long orderId;
    private Long userId;

    // ➕ Thêm điểm trung bình
    private Float averageDeliveryRate;
    private Float averageShopRate;
}
