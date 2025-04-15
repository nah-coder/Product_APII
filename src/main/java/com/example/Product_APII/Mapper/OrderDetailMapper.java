package com.example.Product_APII.Mapper;

import com.example.Product_APII.DTO.Request.OrderDetailRequest;
import com.example.Product_APII.DTO.Response.OrderDetailResponse;
import com.example.Product_APII.Entity.OrderDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "order.id", target = "orderId")
    OrderDetailResponse toDTO(OrderDetail orderDetail);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "orderId", target = "order.id")
    OrderDetail toEntity(OrderDetailRequest dto);
}