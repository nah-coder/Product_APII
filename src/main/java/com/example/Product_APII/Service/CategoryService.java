package com.example.Product_APII.Service;

import com.example.Product_APII.DTO.Request.CategoryRequest;
import com.example.Product_APII.DTO.Response.CategoryResponse;
import com.example.Product_APII.Entity.Category;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Mapper.CategoryMapper;
import com.example.Product_APII.Repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public String create(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        categoryRepository.save(category);
        return "Create category successfully";
    }

    public String update(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateEntityFromDto(request, category);
        categoryRepository.save(category);
        return "Update category successfully";
    }

    public String delete(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
        return "Delete category successfully";
    }

    public CategoryResponse getById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toResponse)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public Map<String, Object> getAll(int page, int size) {
        if (page < 0 || size < 0) {
            throw new AppException(ErrorCode.MUSTNOTBELESSTHANZERO);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        Page<CategoryResponse> categoryResponses = categoryPage.map(categoryMapper::toResponse);

        Map<String, Object> response = new HashMap<>();
        response.put("items", categoryResponses.getContent());
        response.put("currentPage", categoryResponses.getNumber());
        response.put("totalItems", categoryResponses.getTotalElements());
        response.put("totalPages", categoryResponses.getTotalPages());

        return response;
    }

}
