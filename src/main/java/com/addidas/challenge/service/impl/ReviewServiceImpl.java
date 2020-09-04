package com.addidas.challenge.service.impl;

import com.addidas.challenge.entity.Product;
import com.addidas.challenge.entity.Review;
import com.addidas.challenge.exception.ProductNotFoundException;
import com.addidas.challenge.exception.ReviewNotFoundException;
import com.addidas.challenge.repository.ReviewRepository;
import com.addidas.challenge.service.ProductService;
import com.addidas.challenge.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Review saveReview(Review review) {
        LOGGER.info("Review {} was updated at {}", review.getId(), new Date());
        return reviewRepository.save(review);
    }

    @Override
    public void disableReview(Long productId, Long reviewId) {
        reviewRepository.findByActiveTrueAndIdAndProductId(reviewId, productId)
                .map(review -> {
                        review.setActive(false);
                        return reviewRepository.save(review);
                    })
                .orElseThrow(() -> new ReviewNotFoundException(reviewId.toString()));
        LOGGER.info("Review {} is not active for product {}", reviewId, productId);
    }

    @Override
    public Review addReviewToProduct(Review review, Long productId) {
        Optional<Product> product = productService.findById(productId);
        review.setProduct(
                product.orElseThrow(() -> new ProductNotFoundException(productId.toString()))
        );

        Review savedReview = reviewRepository.save(review);
        LOGGER.info("Review {} created for product {}", savedReview.getId(), productId);
        return savedReview;
    }

    @Override
    public Optional<Review> findActiveReviewForIdAndProductId(Long reviewId, Long productId) {
        return reviewRepository.findByActiveTrueAndIdAndProductId(reviewId, productId);
    }

    @Override
    public List<Review> findActiveReviewsForProductId(Long productId, Pageable pageable) {
        return reviewRepository.findByActiveTrueAndProductId(productId, pageable);
    }
}
