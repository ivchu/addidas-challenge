package com.addidas.challenge.facade;

import com.addidas.challenge.dto.FullReviewDto;
import com.addidas.challenge.dto.ReviewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewFacade {
    ReviewDto createReview(Long productId, ReviewDto newReviewDto);

    Optional<ReviewDto> updateReview(Long reviewId, Long productId, ReviewDto reviewDto);

    Boolean deleteReview(Long productId, Long reviewId);

    List<FullReviewDto> findActiveReviewsByProductId(Long productId, Pageable pageable);
}
