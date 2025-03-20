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
@Table(name = "wish_list")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    @Column(name = "wish_list_id", nullable = false)
    private Integer id;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Size(max = 256)
    @Column(name = "wish_list_name", length = 256)
    private String wishListName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "wishlists")
    private Set<Product> products = new HashSet<>();

}