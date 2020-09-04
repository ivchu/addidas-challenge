package com.addidas.challenge.service.impl;

import com.addidas.challenge.entity.Product;
import com.addidas.challenge.entity.Review;
import com.addidas.challenge.repository.ReviewRepository;
import com.addidas.challenge.service.ProductService;
import com.addidas.challenge.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Boolean deleteReview(Long reviewId) {
        Optional<Review> removedReview = reviewRepository.findById(reviewId).map(review -> {
            review.setActive(false);
            return reviewRepository.save(review);
        });
        return removedReview.map(review -> Boolean.TRUE).orElse(Boolean.FALSE);
    }

    @Override
    public Review saveReviewToProduct(Review review, Long productId) {
        Optional<Product> product = productService.findById(productId);
        product.ifPresent(review::setProduct);

        return reviewRepository.save(review);
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
