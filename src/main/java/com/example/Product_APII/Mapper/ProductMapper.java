package com.example.Product_APII.Mapper;


import com.example.Product_APII.DTO.Request.ProductRequest;
import com.example.Product_APII.DTO.Response.ProductResponse;
import com.example.Product_APII.Entity.Category;
import com.example.Product_APII.Entity.Product;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categories", ignore = true)
    Product toEntity(ProductRequest dto);

    @Mapping(target = "categories", expression = "java(mapCategoryNames(product.getCategories()))")
    ProductResponse toResponse(Product product);

    default Set<String> mapCategoryNames(Set<Category> categories) {
        return categories.stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toSet());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProductRequest dto, @MappingTarget Product product);
}
