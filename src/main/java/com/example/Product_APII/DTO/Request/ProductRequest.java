package com.example.Product_APII.DTO.Request;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductRequest {
    private String isbn;
    private String author;
    private Float averageRate;
    private String productName;
    private String description;
    private Float discountPercent;
    private String language;
    private Double listedPrice;
    private Integer pageNumber;
    private Double price;
    private Integer publishingYear;
    private Integer quantity;
    private Integer soldQuantity;
    private Set<Integer> categoryIds;
}

