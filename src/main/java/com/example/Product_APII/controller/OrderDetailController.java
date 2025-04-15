package com.example.Product_APII.controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Response.OrderDetailResponse;
import com.example.Product_APII.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/getOrderDetailsFromOrderId/{orderId}")
    public ResponseEntity<ApiResponse<List<OrderDetailResponse>>> getOrderDetailsFromOrderId(@PathVariable Long orderId){
        List<OrderDetailResponse> order = orderDetailService.getOrderDetailsFromOrder(orderId);
        ApiResponse apiResponse = ApiResponse.builder()
                .code(1000)
                .data(order)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}