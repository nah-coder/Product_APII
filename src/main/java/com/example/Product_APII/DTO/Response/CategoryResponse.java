package com.example.Product_APII.DTO.Response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private Integer id;
    private Integer productQuantity;
    private String categoryName;
}
