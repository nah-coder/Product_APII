package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "delivery_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "delivery_name")
    private String deliveryName;

    @Column(name = "shipping_fee")
    private Double shippingFee;

}