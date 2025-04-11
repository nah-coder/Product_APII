package com.example.Product_APII.Service;


import com.example.Product_APII.DTO.Request.ProductRequest;
import com.example.Product_APII.DTO.Response.ProductResponse;
import com.example.Product_APII.Entity.Category;
import com.example.Product_APII.Entity.Product;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Mapper.ProductMapper;
import com.example.Product_APII.Repository.CategoryRepository;
import com.example.Product_APII.Repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    public String create(ProductRequest request) {
        Product product = productMapper.toEntity(request);

        if (request.getCategoryIds() != null) {
            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(request.getCategoryIds()));
            product.setCategories(categories);
        }

        productRepository.save(product);
        return "Create product successfully";
    }

    public String update(Integer id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        productMapper.updateEntity(request, product);

        if (request.getCategoryIds() != null) {
            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(request.getCategoryIds()));
            product.setCategories(categories);
        }

        productRepository.save(product);
        return "Update product successfully";
    }

    public String delete(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
        return "Delete product successfully";
    }

    public ProductResponse getById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toResponse(product);
    }

    public Map<String, Object> getAll(int page, int size) {
        if (page < 0 || size < 0) {
            throw new AppException(ErrorCode.MUSTNOTBELESSTHANZERO);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Product> productPage = productRepository.findAll(pageable);
        Page<ProductResponse> responsePage = productPage.map(productMapper::toResponse);

        Map<String, Object> response = new HashMap<>();
        response.put("items", responsePage.getContent());
        response.put("currentPage", responsePage.getNumber());
        response.put("totalItems", responsePage.getTotalElements());
        response.put("totalPages", responsePage.getTotalPages());

        return response;
    }
}
