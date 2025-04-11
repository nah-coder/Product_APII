package com.example.Product_APII.controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Request.ProductRequest;
import com.example.Product_APII.DTO.Response.ProductResponse;
import com.example.Product_APII.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> create(@RequestBody @Valid ProductRequest request) {
        String message = productService.create(request);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(1000)
                .message(message)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> update(@PathVariable Integer id,
                                                      @RequestBody @Valid ProductRequest request) {
        String message = productService.update(id, request);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(1000)
                .message(message)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id) {
        String message = productService.delete(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .code(1000)
                .message(message)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Integer id) {
        ProductResponse data = productService.getById(id);
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Map<String, Object> data = productService.getAll(page, size);
        ApiResponse<Map<String, Object>> response = ApiResponse.<Map<String, Object>>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}

