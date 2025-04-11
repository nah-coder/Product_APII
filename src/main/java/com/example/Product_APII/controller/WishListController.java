package com.example.Product_APII.controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Response.WishListResponse;
import com.example.Product_APII.Service.WishListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/wishlists")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    // 1. Thêm hoặc cập nhật sản phẩm vào wishlist
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> createOrUpdateWishlist(
            @RequestParam Integer userId,
            @RequestParam Integer productId) {
        String message = wishListService.createOrUpdateWishlist(userId, productId);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    // 2. Cập nhật toàn bộ danh sách sản phẩm trong wishlist
    @PutMapping("/{wishlistId}")
    public ResponseEntity<ApiResponse<WishListResponse>> updateWishListProduct(
            @PathVariable Integer wishlistId,
            @RequestBody Set<Integer> productIds) {
        WishListResponse data = wishListService.updateWishListProduct(wishlistId, productIds);
        ApiResponse<WishListResponse> response = ApiResponse.<WishListResponse>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    // 3. Xóa sản phẩm khỏi wishlist
    @DeleteMapping("/{wishlistId}/product/{productId}")
    public ResponseEntity<ApiResponse<Object>> removeProduct(
            @PathVariable Integer wishlistId,
            @PathVariable Integer productId) {
        String message = wishListService.removeProductFromWishList(wishlistId, productId);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    // 4. Lấy tất cả wishlist của 1 user
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<WishListResponse>>> getWishlistsByUserId(@PathVariable Integer userId) {
        List<WishListResponse> data = wishListService.getWishlistByUserId(userId);
        ApiResponse<List<WishListResponse>> response = ApiResponse.<List<WishListResponse>>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
