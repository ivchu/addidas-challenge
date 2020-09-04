package com.addidas.challenge.controller;

import com.addidas.challenge.dto.FullReviewDto;
import com.addidas.challenge.dto.ReviewDto;
import com.addidas.challenge.facade.ReviewFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
    @Autowired
    private Map<String, Sort> sortingMap;
    @Autowired
    private ReviewFacade reviewFacade;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ReviewDto createReview(@PathVariable Long productId, @Valid @RequestBody ReviewDto newReview) {
        return reviewFacade.createReview(productId, newReview);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteReview(@PathVariable Long productId, @PathVariable Long reviewId) {
        reviewFacade.deleteReview(productId, reviewId);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Object> updateReview(@PathVariable Long productId,
                                               @PathVariable Long reviewId,
                                               @RequestBody ReviewDto reviewDto) {
        Optional<ReviewDto> shortReviewDto = reviewFacade.updateReview(reviewId, productId, reviewDto);
        return shortReviewDto.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<FullReviewDto>> getReviews(@PathVariable Long productId,
                                                          @RequestParam(required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                          @RequestParam(required = false, defaultValue = "dateAsc") String sortBy) {
        Sort sort = sortingMap.get(sortBy.toLowerCase());
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        List<FullReviewDto> activeReviews = reviewFacade.findActiveReviewsByProductId(productId, pageable);
        return ResponseEntity.ok(activeReviews);
    }

    public void setSortingMap(Map<String, Sort> sortingMap) {
        this.sortingMap = sortingMap;
    }
}