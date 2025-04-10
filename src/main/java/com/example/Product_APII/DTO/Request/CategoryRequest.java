package com.example.Product_APII.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    @NotNull
    private Integer productQuantity;

    @Size(max = 256)
    private String categoryName;
}

