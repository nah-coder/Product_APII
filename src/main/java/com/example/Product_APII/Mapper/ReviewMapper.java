package com.example.Product_APII.Mapper;

import com.example.Product_APII.DTO.Request.ReviewRequest;
import com.example.Product_APII.DTO.Response.ReviewResponse;
import com.example.Product_APII.Entity.Review;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderReview.id", target = "orderReviewId")
    ReviewResponse toResponse(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "isHide", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderReview", ignore = true)
    Review toEntity(ReviewRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ReviewRequest request, @MappingTarget Review review);

    // Phương thức mới để chuyển đổi danh sách Review sang danh sách ReviewResponse
    List<ReviewResponse> toResponseList(List<Review> reviews);
}
