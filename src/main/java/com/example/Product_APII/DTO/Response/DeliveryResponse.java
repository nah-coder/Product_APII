package com.example.Product_APII.DTO.Response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponse {
    private Integer id;
    private String deliveryName;
    private Double shippingFee;
}
