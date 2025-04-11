package com.example.Product_APII.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRequest {
    private String deliveryName;
    private Double shippingFee;
}
