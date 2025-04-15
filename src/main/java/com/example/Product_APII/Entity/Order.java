package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "date")
    private Instant date;

    @Size(max = 255)
    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Size(max = 255)
    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Size(max = 255)
    @Column(name = "note_from_user")
    private String noteFromUser;

    @Size(max = 255)
    @NotNull
    @Column(name = "order_code", nullable = false)
    private String orderCode;

    @Size(max = 255)
    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "payment_cost")
    private Double paymentCost;

    @Size(max = 255)
    @Column(name = "purchase_address")
    private String purchaseAddress;

    @Size(max = 255)
    @Column(name = "reason_cancel_order")
    private String reasonCancelOrder;

    @Column(name = "shipping_fee")
    private Double shippingFee;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_product")
    private Integer totalProduct;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

}