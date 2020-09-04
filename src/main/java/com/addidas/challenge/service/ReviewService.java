package com.addidas.challenge.service;

import com.addidas.challenge.entity.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review saveReview(Review review);

    Boolean deleteReview(Long reviewId);

    Review saveReviewToProduct(Review review, Long productId);

    Optional<Review> findActiveReviewForIdAndProductId(Long reviewId, Long productId);

    List<Review> findActiveReviewsForProductId(Long productId, Pageable pageable);
}
