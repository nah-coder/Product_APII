package com.example.Product_APII.Repository;

import com.example.Product_APII.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long productId);
    List<Image> findByReviewId(Long reviewId);
    List<Image> findByUserId(Long userId);
}
