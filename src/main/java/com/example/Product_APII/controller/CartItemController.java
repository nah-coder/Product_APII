package com.example.Product_APII.controller;


import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Request.CartItemRequest;
import com.example.Product_APII.DTO.Response.CartItemResponse;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CartItemResponse>>> getCartItems(@RequestAttribute User user) {
        List<CartItemResponse> data = cartItemService.getCartItems(user);
        return ResponseEntity.ok(ApiResponse.<List<CartItemResponse>>builder()
                .code(1000)
                .data(data)
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CartItemResponse>> addToCart(@RequestAttribute User user,
                                                                   @RequestBody CartItemRequest request) {
        CartItemResponse data = cartItemService.addToCart(user, request);
        return ResponseEntity.ok(ApiResponse.<CartItemResponse>builder()
                .code(1000)
                .data(data)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CartItemResponse>> updateQuantity(@RequestAttribute User user,
                                                                        @PathVariable("id") int cartItemId,
                                                                        @RequestParam("qty") int qty) {
        CartItemResponse data = cartItemService.updateCartItemQuantity(user, cartItemId, qty);
        return ResponseEntity.ok(ApiResponse.<CartItemResponse>builder()
                .code(1000)
                .data(data)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> removeItem(@RequestAttribute User user,
                                                          @PathVariable("id") int cartItemId) {
        cartItemService.removeCartItem(user, cartItemId);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(1000)
                .data(null)
                .build());
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<Object>> clearCart(@RequestAttribute User user) {
        cartItemService.clearCart(user);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(1000)
                .message("Xóa toàn bộ giỏ hàng thành công")
                .data(null)
                .build());
    }
}
