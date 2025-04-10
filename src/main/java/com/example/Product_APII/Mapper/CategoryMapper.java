package com.example.Product_APII.Mapper;


import com.example.Product_APII.DTO.Request.CategoryRequest;
import com.example.Product_APII.DTO.Response.CategoryResponse;
import com.example.Product_APII.Entity.Category;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CategoryRequest dto, @MappingTarget Category entity);
}
