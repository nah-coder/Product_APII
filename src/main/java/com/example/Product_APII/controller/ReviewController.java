package com.example.Product_APII.controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Request.ReviewRequest;
import com.example.Product_APII.DTO.Response.ReviewResponse;
import com.example.Product_APII.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Tạo mới đánh giá
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@RequestBody ReviewRequest request,
                                                                    Authentication authentication) {
        ReviewResponse reviewResponse = reviewService.createReview(request, authentication.getName());
        ApiResponse<ReviewResponse> response = ApiResponse.<ReviewResponse>builder()
                .code(1000)
                .data(reviewResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    // Cập nhật đánh giá
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(@PathVariable Long id,
                                                                    @RequestBody ReviewRequest request,
                                                                    Authentication authentication) {
        // Đảm bảo phương thức service có thể xử lý các lỗi và trả về thông báo thích hợp.
        ReviewResponse reviewResponse = reviewService.updateReview(id, request, authentication.getName());
        ApiResponse<ReviewResponse> response = ApiResponse.<ReviewResponse>builder()
                .code(1000)
                .data(reviewResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    // Xóa đánh giá
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteReview(@PathVariable Long id,
                                                            Authentication authentication) {
        reviewService.deleteReview(id, authentication.getName());
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message("Đánh giá đã được xóa thành công")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    // Lấy danh sách đánh giá của một sản phẩm
    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviewsByProduct(@PathVariable int productId) {
        List<ReviewResponse> reviewResponses = reviewService.getReviewsByProductId(productId);
        ApiResponse<List<ReviewResponse>> response = ApiResponse.<List<ReviewResponse>>builder()
                .code(1000)
                .data(reviewResponses)
                .build();
        return ResponseEntity.ok(response);
    }

    // Lấy chi tiết một đánh giá
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviewById(@PathVariable int id) {
        // Nếu không tìm thấy review, service nên ném exception (ví dụ: ResourceNotFoundException).
        List<ReviewResponse> reviewResponse = reviewService.getReviewsByUserId(id);
        ApiResponse<List<ReviewResponse>> response = ApiResponse.<List<ReviewResponse>>builder()
                .code(1000)
                .data(reviewResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
