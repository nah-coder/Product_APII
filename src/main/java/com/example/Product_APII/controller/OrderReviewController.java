package com.example.Product_APII.Controller;

import com.example.Product_APII.DTO.Request.OrderReviewRequest;
import com.example.Product_APII.DTO.Response.OrderReviewResponse;
import com.example.Product_APII.Service.OrderReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-reviews")
@RequiredArgsConstructor
public class OrderReviewController {

    private final OrderReviewService orderReviewService;

    // ✅ Thêm đánh giá
    @PostMapping("/{orderId}/users/{userId}")
    public ResponseEntity<OrderReviewResponse> addReview(
            @PathVariable Long orderId,
            @PathVariable int userId,
            @RequestBody OrderReviewRequest request
    ) {
        OrderReviewResponse response = orderReviewService.addReview(orderId, userId, request);
        return ResponseEntity.ok(response);
    }

    // ✅ Lấy đánh giá
    @GetMapping("/{orderId}/users/{userId}")
    public ResponseEntity<OrderReviewResponse> getReview(
            @PathVariable Long orderId,
            @PathVariable int userId
    ) {
        OrderReviewResponse response = orderReviewService.getReviewByOrderId(orderId, userId);
        return ResponseEntity.ok(response);
    }
}
