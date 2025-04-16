package com.example.Product_APII.Service;

import com.example.Product_APII.DTO.Request.ReviewRequest;
import com.example.Product_APII.DTO.Response.ReviewResponse;
import com.example.Product_APII.Entity.OrderReview;
import com.example.Product_APII.Entity.Product;
import com.example.Product_APII.Entity.Review;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Mapper.ReviewMapper;
import com.example.Product_APII.Repository.OrderReviewRepository;
import com.example.Product_APII.Repository.ProductRepository;
import com.example.Product_APII.Repository.ReviewRepository;
import com.example.Product_APII.Repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UsersRepository userRepository;
    private final OrderReviewRepository orderReviewRepository;
    private final ReviewMapper reviewMapper;

    // Tạo mới một đánh giá sản phẩm
    public ReviewResponse createReview(ReviewRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        OrderReview orderReview = orderReviewRepository.findById(request.getOrderReviewId())
                .orElseThrow(() -> new RuntimeException("Đơn hàng không tồn tại"));

        if (!Objects.equals(orderReview.getUser().getId(), user.getId())) {
            throw new RuntimeException("Không thể đánh giá sản phẩm không thuộc đơn hàng của bạn");
        }

        // Kiểm tra xem người dùng đã đánh giá sản phẩm này chưa
        if (reviewRepository.existsByProductIdAndUserId(product.getId(), user.getId())) {
            throw new RuntimeException("Bạn đã đánh giá sản phẩm này rồi");
        }

        // Tạo review mới
        Review review = reviewMapper.toEntity(request);
        review.setUser(user);
        review.setProduct(product);
        review.setOrderReview(orderReview);
        review.setDate(Instant.now());
        review.setIsHide(false);

        // Lưu review mới
        reviewRepository.save(review);

        // Cập nhật điểm trung bình cho sản phẩm
        updateAverageRating(product);

        return reviewMapper.toResponse(review);
    }

    // Cập nhật điểm trung bình cho sản phẩm sau khi có sự thay đổi trong các đánh giá
    private void updateAverageRating(Product product) {
        List<Review> reviews = reviewRepository.findByProductId(product.getId());
        if (reviews.isEmpty()) {
            return; // Không có đánh giá nào
        }

        float totalStars = 0;
        for (Review review : reviews) {
            totalStars += review.getRate(); // Lấy điểm sao của mỗi review
        }

        float averageRating = totalStars / reviews.size(); // Tính điểm trung bình
        product.setAverageRate(averageRating); // Cập nhật điểm trung bình vào sản phẩm
        productRepository.save(product); // Lưu lại sản phẩm
    }

    // Cập nhật một đánh giá
    public ReviewResponse updateReview(Long id, ReviewRequest request, String email) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review không tồn tại"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        if (!Objects.equals(review.getUser().getId(), user.getId())) {
            throw new RuntimeException("Bạn không có quyền sửa đánh giá này");
        }

        // Cập nhật review
        reviewMapper.updateEntityFromRequest(request, review);
        review.setDate(Instant.now());

        // Lưu lại review đã cập nhật
        reviewRepository.save(review);

        // Cập nhật lại điểm trung bình của sản phẩm sau khi sửa review
        updateAverageRating(review.getProduct());

        return reviewMapper.toResponse(review);
    }

    // Xóa một đánh giá
    @Transactional
    public void deleteReview(Long id, String email) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review không tồn tại"));

        if (!review.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Bạn không có quyền xóa đánh giá này");
        }

        Product product = review.getProduct();

        // Xóa review
        reviewRepository.delete(review);

        // Cập nhật lại điểm trung bình của sản phẩm sau khi xóa review
        updateAverageRating(product);
    }

    // Lấy danh sách các đánh giá của một sản phẩm
    public List<ReviewResponse> getReviewsByProductId(int productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviewMapper.toResponseList(reviews);
    }

    // Lấy danh sách các đánh giá của người dùng
    public List<ReviewResponse> getReviewsByUserId(int userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviewMapper.toResponseList(reviews);
    }

    // Lấy điểm trung bình của sản phẩm
    public float getAverageRating(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        return product.getAverageRate(); // Trả về điểm trung bình
    }
}

