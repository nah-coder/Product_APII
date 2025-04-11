package com.example.Product_APII.controller;


import com.example.Product_APII.DTO.Request.PaymentRequest;
import com.example.Product_APII.DTO.Response.PaymentResponse;
import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 1. Tạo mới payment
    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(@RequestBody PaymentRequest request) {
        PaymentResponse responseData = paymentService.createPayment(request);
        ApiResponse<PaymentResponse> response = ApiResponse.<PaymentResponse>builder()
                .code(1000)
                .message(responseData)
                .build();
        return ResponseEntity.ok(response);
    }

    // 2. Lấy tất cả payment
    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayments() {
        List<PaymentResponse> data = paymentService.getAllPayments();
        ApiResponse<List<PaymentResponse>> response = ApiResponse.<List<PaymentResponse>>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    // 3. Lấy payment theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable Integer id) {
        PaymentResponse data = paymentService.getPaymentById(id);
        ApiResponse<PaymentResponse> response = ApiResponse.<PaymentResponse>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    // 4. Cập nhật payment
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> updatePayment(
            @PathVariable Integer id,
            @RequestBody PaymentRequest request) {
        PaymentResponse data = paymentService.updatePayment(id, request);
        ApiResponse<PaymentResponse> response = ApiResponse.<PaymentResponse>builder()
                .code(1000)
                .message(data)
                .build();
        return ResponseEntity.ok(response);
    }

    // 5. Xóa payment
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePayment(@PathVariable Integer id) {
        String message = paymentService.deletePayment(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
