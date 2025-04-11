package com.example.Product_APII.DTO.Request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String deliveryAddress;
    private String noteFromUser;
    private String purchaseAddress;
    private Double shippingFee;
    private Double totalPrice;
    private Integer totalProduct;
    private Double paymentCost;
    private String orderCode;

    private Integer deliveryId;
    private Integer paymentId;
    private Integer userId;
}
