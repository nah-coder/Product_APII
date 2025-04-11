package com.example.Product_APII.DTO.Response;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class WishListResponse {
    private Integer id;
    private String wishListName;
    private Integer quantity;
    private Integer userId;
    private Set<Integer> productIds;
}
