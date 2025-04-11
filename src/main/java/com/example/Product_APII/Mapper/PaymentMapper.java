package com.example.Product_APII.Mapper;

import com.example.Product_APII.DTO.Request.PaymentRequest;
import com.example.Product_APII.DTO.Response.PaymentResponse;
import com.example.Product_APII.Entity.Payment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toEntity(PaymentRequest request);

    PaymentResponse toResponse(Payment entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PaymentRequest dto, @MappingTarget Payment entity);
}
