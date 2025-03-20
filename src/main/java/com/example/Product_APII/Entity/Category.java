package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;

    @Size(max = 256)
    @Column(name = "category_name", length = 256)
    private String categoryName;

    @ManyToMany(mappedBy = "categories") // Quan hệ ngược lại, không cần JoinTable nữa
    private Set<Product> products = new HashSet<>();
}