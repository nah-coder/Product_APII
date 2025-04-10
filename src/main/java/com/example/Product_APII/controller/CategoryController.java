package com.example.Product_APII.controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.DTO.Request.CategoryRequest;
import com.example.Product_APII.DTO.Response.CategoryResponse;
import com.example.Product_APII.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> create(@RequestBody @Valid CategoryRequest request) {
        String message = categoryService.create(request);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> update(@PathVariable Integer id,
                                                      @RequestBody @Valid CategoryRequest request) {
        String message = categoryService.update(id, request);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Integer id) {
        String message = categoryService.delete(id);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Integer id) {
        CategoryResponse data = categoryService.getById(id);
        ApiResponse<CategoryResponse> response = ApiResponse.<CategoryResponse>builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> data = categoryService.getAll(page, size);
        ApiResponse<Object> response = ApiResponse.builder()
                .code(1000)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
