package com.addidas.challenge.controller;

import com.addidas.challenge.dto.ReviewDto;
import com.addidas.challenge.dto.ShortReviewDto;
import com.addidas.challenge.entity.Product;
import com.addidas.challenge.entity.Review;
import com.addidas.challenge.mapper.ReviewMapper;
import com.addidas.challenge.repository.ProductRepository;
import com.addidas.challenge.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
    @Autowired
    private Map<String, Sort> sortingMap;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewMapper reviewMapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ShortReviewDto createReview(@PathVariable Long productId, @RequestBody ReviewDto newReview){
        Review review = reviewMapper.createReview(newReview);
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(review::setProduct);

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.mapToReviewDto(savedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Long productId, @PathVariable Long reviewId){
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (!reviewOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reviewOptional.ifPresent(review -> {
                    review.setActive(false);
                    reviewRepository.save(review);
                });
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity updateReview(@PathVariable Long productId, @PathVariable Long reviewId, @RequestBody ReviewDto reviewDto){
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (!reviewOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reviewOptional.ifPresent(review -> {
                    reviewMapper.updateReview(reviewDto, review);
                    reviewRepository.save(review);
                });
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<Object> getReviews(@PathVariable Long productId,
                                     @RequestParam(required = false, defaultValue = "0") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     @RequestParam(required = false, defaultValue = "dateAsc") String sortBy){
        Sort sort = sortingMap.get(sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        List<Review> reviews = reviewRepository.findByActiveTrueAndProductId(productId, pageable);
        List<ReviewDto> reviewDtos = reviewMapper.mapToReviewDtos(reviews);
        return ResponseEntity.ok(reviewDtos);
    }
}
