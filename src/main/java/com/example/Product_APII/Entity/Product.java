package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "isbn")
    private String isbn;

    @Size(max = 512)
    @NotNull
    @Column(name = "author", nullable = false, length = 512)
    private String author;

    @NotNull
    @Column(name = "average_rate", nullable = false)
    private Float averageRate;

    @Size(max = 255)
    @NotNull
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "discount_percent")
    private Float discountPercent;

    @Size(max = 255)
    @NotNull
    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "listed_price")
    private Double listedPrice;

    @NotNull
    @Column(name = "page_number", nullable = false)
    private Integer pageNumber;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "publishing_year", nullable = false)
    private Integer publishingYear;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "sold_quantity", nullable = false)
    private Integer soldQuantity;

    @ManyToMany
    @JoinTable(
            name = "product_category", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "product_id"), // Khóa ngoại trỏ đến Product
            inverseJoinColumns = @JoinColumn(name = "category_id") // Khóa ngoại trỏ đến Category
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "product_wishlist", // Bảng trung gian
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "wishlist_id")
    )
    private Set<WishList> wishlists = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new HashSet<>();

}