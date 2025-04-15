//package com.example.Product_APII.Mapper;
//
//
//import com.example.Product_APII.DTO.Response.ReviewResponse;
//import com.example.Product_APII.Entity.Review;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(componentModel = "spring")
//public interface ReviewMapper {
//
//    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
//
//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "book.id", target = "bookId")
//    @Mapping(source = "orderDetail.id", target = "orderDetailId")
//    ReviewResponse toReviewResponse(Review review);
//}
