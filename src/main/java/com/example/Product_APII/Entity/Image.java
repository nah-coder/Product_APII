package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "image_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "image_data")
    private String imageData;

    @Column(name = "is_icon")
    private Boolean isIcon;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}