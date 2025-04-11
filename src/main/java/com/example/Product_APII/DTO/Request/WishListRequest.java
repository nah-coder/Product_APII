package com.example.Product_APII.DTO.Request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class WishListRequest {

    @Size(max = 256)
    private String wishListName;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer userId;

    private Set<Integer> productIds;
}
