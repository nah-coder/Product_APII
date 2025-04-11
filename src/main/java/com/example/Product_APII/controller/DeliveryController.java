package com.example.Product_APII.controller;


import com.example.Product_APII.DTO.Request.DeliveryRequest;
import com.example.Product_APII.DTO.Response.DeliveryResponse;
import com.example.Product_APII.Service.DeliveryService;
import com.example.Product_APII.DTO.Request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // ✅ Tạo mới một phương thức giao hàng
    @PostMapping
    public ResponseEntity<ApiResponse<DeliveryResponse>> createDelivery(@RequestBody DeliveryRequest request) {
        DeliveryResponse response = deliveryService.create(request);
        return ResponseEntity.ok(ApiResponse.<DeliveryResponse>builder()
                .code(1000)
                .data(response)
                .build());
    }

    // ✅ Cập nhật thông tin giao hàng theo ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryResponse>> updateDelivery(@PathVariable Integer id, @RequestBody DeliveryRequest request) {
        DeliveryResponse response = deliveryService.update(id, request);
        return ResponseEntity.ok(ApiResponse.<DeliveryResponse>builder()
                .code(1000)
                .data(response)
                .build());
    }

    // ✅ Xóa một phương thức giao hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDelivery(@PathVariable Integer id) {
        String message = deliveryService.delete(id);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .code(1000)
                .message(message)
                .build());
    }

    // ✅ Lấy tất cả các phương thức giao hàng
    @GetMapping
    public ResponseEntity<ApiResponse<List<DeliveryResponse>>> getAllDeliveries() {
        List<DeliveryResponse> responses = deliveryService.getAll();
        return ResponseEntity.ok(ApiResponse.<List<DeliveryResponse>>builder()
                .code(1000)
                .data(responses)
                .build());
    }

    // ✅ Lấy chi tiết giao hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryResponse>> getDeliveryById(@PathVariable Integer id) {
        DeliveryResponse response = deliveryService.getById(id);
        return ResponseEntity.ok(ApiResponse.<DeliveryResponse>builder()
                .code(1000)
                .data(response)
                .build());
    }

    // ✅ Tìm giao hàng theo tên
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DeliveryResponse>>> findByDeliveryName(@RequestParam String name) {
        List<DeliveryResponse> responses = deliveryService.findByDeliveryName(name);
        return ResponseEntity.ok(ApiResponse.<List<DeliveryResponse>>builder()
                .code(1000)
                .data(responses)
                .build());
    }
}

