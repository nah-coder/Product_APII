package com.example.Product_APII.Service;

import com.example.Product_APII.DTO.Request.OrderReviewRequest;
import com.example.Product_APII.DTO.Response.OrderReviewResponse;
import com.example.Product_APII.Entity.OrderReview;
import com.example.Product_APII.Entity.Order;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Repository.OrderReviewRepository;
import com.example.Product_APII.Repository.OrderRepository;
import com.example.Product_APII.Mapper.OrderReviewMapper;
import com.example.Product_APII.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderReviewService {

    private final OrderReviewRepository orderReviewRepository;
    private final OrderRepository orderRepository;
    private final UsersRepository userRepository;
    private final OrderReviewMapper orderReviewMapper;

    // ✅ Thêm mới đánh giá
    @Transactional
    public OrderReviewResponse addReview(Long orderId, int userId, OrderReviewRequest request) {
        // Kiểm tra đơn hàng tồn tại
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Kiểm tra người dùng tồn tại
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra xem người dùng đã đánh giá đơn hàng này chưa
        Optional<OrderReview> existingReview = orderReviewRepository.findByOrderIdAndUserId(orderId, userId);
        if (existingReview.isPresent()) {
            throw new RuntimeException("User has already reviewed this order.");
        }

        // Tạo đối tượng OrderReview từ request
        OrderReview orderReview = orderReviewMapper.toEntity(request);
        orderReview.setOrder(order);
        orderReview.setUser(user);

        // Lưu đánh giá vào cơ sở dữ liệu
        OrderReview savedReview = orderReviewRepository.save(orderReview);

        // Tính điểm trung bình
        float averageShopRate = 0;
        float averageDeliveryRate = 0;
        var allReviews = orderReviewRepository.findAll();
        int totalShop = 0, totalDelivery = 0;
        float sumShop = 0, sumDelivery = 0;

        for (OrderReview review : allReviews) {
            float shopRate = review.getShopRate();
            float deliveryRate = review.getDeliveryRate();
            if (shopRate >= 1 && shopRate <= 5) {
                sumShop += shopRate;
                totalShop++;
            }
            if (deliveryRate >= 1 && deliveryRate <= 5) {
                sumDelivery += deliveryRate;
                totalDelivery++;
            }
        }

        if (totalShop > 0) averageShopRate = sumShop / totalShop;
        if (totalDelivery > 0) averageDeliveryRate = sumDelivery / totalDelivery;

        // Tạo response và set điểm trung bình
        OrderReviewResponse reviewResponse = orderReviewMapper.toResponse(savedReview);
        reviewResponse.setAverageShopRate(averageShopRate);
        reviewResponse.setAverageDeliveryRate(averageDeliveryRate);

        return reviewResponse;
    }

    // ✅ Lấy đánh giá của đơn hàng
    public OrderReviewResponse getReviewByOrderId(Long orderId, int userId) {
        OrderReview orderReview = orderReviewRepository.findByOrderIdAndUserId(orderId, userId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return orderReviewMapper.toResponse(orderReview);
    }
}
