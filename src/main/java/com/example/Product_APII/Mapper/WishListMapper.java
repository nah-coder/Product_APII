package com.example.Product_APII.Mapper;

import com.example.Product_APII.DTO.Request.WishListRequest;
import com.example.Product_APII.DTO.Response.WishListResponse;
import com.example.Product_APII.Entity.WishList;
import org.mapstruct.*;

import java.util.stream.Collectors; // ✅ Dòng này rất quan trọng!!!

@Mapper(componentModel = "spring")
public interface WishListMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "products", ignore = true)
    WishList toEntity(WishListRequest request);

    @Mapping(source = "user.id", target = "userId")
//    @Mapping(target = "productIds", expression = "java(entity.getProducts().stream().map(p -> p.getId()).collect(Collectors.toSet()))")
    WishListResponse toResponse(WishList entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(WishListRequest dto, @MappingTarget WishList entity);
}
