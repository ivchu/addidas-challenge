package com.addidas.challenge.service;

import com.addidas.challenge.entity.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service for working with Review entities
 */
public interface ReviewService {
    /**
     * @param review review to save
     * @return saved review
     */
    Review saveReview(Review review);

    /**
     * Makes review inactive
     * @param productId product which handles review
     * @param reviewId review Id
     */
    void disableReview(Long productId, Long reviewId);

    /**
     * Saves review and adds it to product
     * @param review to add
     * @param productId parent product
     * @return saved review
     */
    Review addReviewToProduct(Review review, Long productId);

    /**
     * Find active review by id for product
     * @param reviewId to search
     * @param productId review parent product
     * @return Optional of review if such exists
     */
    Optional<Review> findActiveReviewForIdAndProductId(Long reviewId, Long productId);

    /**
     * Review search with pagination and sorts
     * @param productId whose reviews to return
     * @param pageable sort and pagination preferences
     * @return paginated and sorted list of reviews
     */
    List<Review> findActiveReviewsForProductId(Long productId, Pageable pageable);
}
