package com.example.Product_APII.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private String type;
    private Long size;

    private Long mappedId;      // ID của thực thể (userId, productId, postId...)
    private String entityType;  // Tên thực thể: "user", "product", "post", ...
}
