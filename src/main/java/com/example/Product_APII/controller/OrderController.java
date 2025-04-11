package com.example.Product_APII.controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Request.OrderRequest;
import com.example.Product_APII.DTO.Response.OrderResponse;
import com.example.Product_APII.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Tạo mới đơn hàng
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> create(@RequestBody @Valid OrderRequest request) {
        String message = orderService.create(request);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    // Cập nhật đơn hàng theo ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> update(@PathVariable Long id,
                                                      @RequestBody @Valid OrderRequest request) {
        String message = orderService.update(id, request);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    // Xoá đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        String message = orderService.delete(id);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    // Lấy đơn hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getById(@PathVariable Long id) {
        OrderResponse data = orderService.getById(id);
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    // Lấy tất cả đơn hàng (có phân trang)
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> data = orderService.getAll(page, size);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .data(data)
                .message("Lấy danh sách đơn hàng thành công")
                .build();
        return ResponseEntity.ok(response);
    }

    // Lấy danh sách đơn hàng theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getByUserId(@PathVariable Integer userId) {
        List<OrderResponse> data = orderService.getByUserId(userId);
        ApiResponse<List<OrderResponse>> response = ApiResponse.<List<OrderResponse>>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
