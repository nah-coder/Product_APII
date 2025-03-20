package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "review_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @Size(max = 255)
    @Column(name = "image_five")
    private String imageFive;

    @Size(max = 255)
    @Column(name = "image_four")
    private String imageFour;

    @Size(max = 255)
    @Column(name = "image_one")
    private String imageOne;

    @Size(max = 255)
    @Column(name = "image_three")
    private String imageThree;

    @Size(max = 255)
    @Column(name = "image_two")
    private String imageTwo;

    @NotNull
    @ColumnDefault("b'0'")
    @Column(name = "is_hide", nullable = false)
    private Boolean isHide = false;

    @Column(name = "rate")
    private Float rate;

    @Size(max = 255)
    @Column(name = "video")
    private String video;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_review_id", nullable = false)
    private OrderReview orderReview;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}