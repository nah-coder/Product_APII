package com.example.Product_APII.DTO.Response;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductResponse {
    private Integer id;
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
    private Set<String> categories;
}
