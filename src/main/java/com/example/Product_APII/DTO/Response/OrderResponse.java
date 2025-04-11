package com.example.Product_APII.DTO.Response;


import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Instant date;
    private String deliveryAddress;
    private String deliveryStatus;
    private String noteFromUser;
    private String orderCode;
    private String orderStatus;
    private Double paymentCost;
    private String purchaseAddress;
    private String reasonCancelOrder;
    private Double shippingFee;
    private Double totalPrice;
    private Integer totalProduct;

    private Integer deliveryId;
    private Integer paymentId;
    private Integer userId;
}
