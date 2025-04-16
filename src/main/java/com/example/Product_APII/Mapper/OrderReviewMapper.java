package com.example.Product_APII.Mapper;

import com.example.Product_APII.DTO.Request.OrderReviewRequest;
import com.example.Product_APII.DTO.Response.OrderReviewResponse;
import com.example.Product_APII.Entity.OrderReview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderReviewMapper {

    // Chuyển từ OrderReviewRequest (DTO) thành OrderReview (Entity)
    OrderReview toEntity(OrderReviewRequest request);

    // Chuyển từ OrderReview (Entity) thành OrderReviewResponse (DTO)
    OrderReviewResponse toResponse(OrderReview orderReview);
}
