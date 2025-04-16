package com.example.Product_APII.Repository;

import com.example.Product_APII.Entity.OrderReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderReviewRepository extends JpaRepository<OrderReview, Long> {

    // Tìm kiếm OrderReview dựa trên orderId và userId
    Optional<OrderReview> findByOrderIdAndUserId(Long orderId, int userId);
}
