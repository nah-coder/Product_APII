package com.example.Product_APII.Mapper;


import com.example.Product_APII.DTO.Request.DeliveryRequest;
import com.example.Product_APII.DTO.Response.DeliveryResponse;
import com.example.Product_APII.Entity.Delivery;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    // Map từ DTO sang Entity khi tạo mới
    Delivery toEntity(DeliveryRequest request);

    // Map từ Entity sang Response DTO
    DeliveryResponse toResponse(Delivery delivery);

    // Cập nhật entity từ DTO (chỉ cập nhật nếu có giá trị mới)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DeliveryRequest dto, @MappingTarget Delivery delivery);
}
