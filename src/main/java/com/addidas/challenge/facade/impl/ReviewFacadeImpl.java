package com.addidas.challenge.facade.impl;

import com.addidas.challenge.dto.FullReviewDto;
import com.addidas.challenge.dto.ReviewDto;
import com.addidas.challenge.entity.Review;
import com.addidas.challenge.facade.ReviewFacade;
import com.addidas.challenge.mapper.FullReviewMapper;
import com.addidas.challenge.mapper.ReviewMapper;
import com.addidas.challenge.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReviewFacadeImpl implements ReviewFacade {
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private FullReviewMapper fullReviewMapper;
    @Autowired
    private ReviewService reviewService;


    @Override
    public ReviewDto createReview(Long productId, ReviewDto newReviewDto) {
        Review review = reviewMapper.createReview(newReviewDto);
        return reviewMapper.mapToReviewDto(reviewService.addReviewToProduct(review, productId));
    }

    @Override
    public Optional<ReviewDto> updateReview(Long reviewId, Long productId, ReviewDto reviewDto) {
        return reviewService.findActiveReviewForIdAndProductId(reviewId, productId)
                .map(review -> {
                    reviewMapper.updateReview(reviewDto, review);
                    return reviewService.saveReview(review);
                })
                .map(review -> reviewMapper.mapToReviewDto(review));
    }

    @Override
    public void deleteReview(Long productId, Long reviewId) {
        reviewService.disableReview(productId, reviewId);
    }

    @Override
    public List<FullReviewDto> findActiveReviewsByProductId(Long productId, Pageable pageable) {
        return fullReviewMapper.mapToReviewDtos(reviewService.findActiveReviewsForProductId(productId, pageable));
    }
}
