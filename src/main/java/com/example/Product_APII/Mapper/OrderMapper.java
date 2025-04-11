package com.example.Product_APII.Mapper;


import com.example.Product_APII.DTO.Request.OrderRequest;
import com.example.Product_APII.DTO.Response.OrderResponse;
import com.example.Product_APII.Entity.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", expression = "java(java.time.Instant.now())")
    @Mapping(target = "orderStatus", constant = "PENDING")
    @Mapping(target = "deliveryStatus", constant = "CHƯA GIAO")
    @Mapping(target = "delivery.id", source = "deliveryId")
    @Mapping(target = "payment.id", source = "paymentId")
    @Mapping(target = "user.id", source = "userId")
    Order toEntity(OrderRequest request);

    @Mapping(source = "delivery.id", target = "deliveryId")
    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(source = "user.id", target = "userId")
    OrderResponse toResponse(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(OrderRequest request, @MappingTarget Order order);
}
