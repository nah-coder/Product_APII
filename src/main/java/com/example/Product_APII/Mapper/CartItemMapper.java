package com.example.Product_APII.Mapper;


import com.example.Product_APII.DTO.Response.CartItemResponse;
import com.example.Product_APII.Entity.CartItem;

public class CartItemMapper {

    public static CartItemResponse toDTO(CartItem cartItem) {
        CartItemResponse dto = new CartItemResponse();
        dto.setId(cartItem.getId());
        dto.setProductName(cartItem.getProduct().getProductName());
        dto.setQuantity(cartItem.getQuantity());
        dto.setProductPrice(cartItem.getProduct().getPrice());
        dto.setUserId(cartItem.getUser().getId());
        return dto;
    }
}
