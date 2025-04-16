package com.example.Product_APII.Repository;

import com.example.Product_APII.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Lấy danh sách đánh giá theo sản phẩm
    List<Review> findByProductId(int productId);
    List<Review> findByUserId(int productId);

    // Kiểm tra xem user đã đánh giá sản phẩm chưa
    boolean existsByProductIdAndUserId(int productId, int userId);
}
