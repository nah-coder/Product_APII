package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "payment_name")
    private String paymentName;

}