package com.addidas.challenge.facade;

import com.addidas.challenge.dto.FullReviewDto;
import com.addidas.challenge.dto.ReviewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Facade for working with reviews
 */
public interface ReviewFacade {
    /**
     * Creates new review
     *
     * @param productId review`s productId
     * @param newReviewDto data for creation
     * @return newly created review
     */
    ReviewDto createReview(Long productId, ReviewDto newReviewDto);

    /**
     * Updates review with id for specific product
     * @param reviewId to update
     * @param productId review`s product
     * @param reviewDto data to update
     * @return updated review
     */
    Optional<ReviewDto> updateReview(Long reviewId, Long productId, ReviewDto reviewDto);

    /**
     * Removes review
     * @param productId review`s product
     * @param reviewId to remove
     */
    void deleteReview(Long productId, Long reviewId);

    /**
     * Review search with pagination and sorts
     * @param productId whose reviews to return
     * @param pageable sort and pagination preferences
     * @return paginated and sorted list of review DTOs
     */
    List<FullReviewDto> findActiveReviewsByProductId(Long productId, Pageable pageable);
}
