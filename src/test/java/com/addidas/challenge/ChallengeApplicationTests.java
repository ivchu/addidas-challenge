package com.addidas.challenge;

import com.addidas.challenge.dto.ReviewDto;
import com.addidas.challenge.entity.Review;
import com.addidas.challenge.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChallengeApplicationTests {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void newReviewCreatedWhenProductExists() throws Exception {
        ReviewDto reviewDto = new ReviewDto();
        String reviewText = "777_test";
        reviewDto.setText(reviewText);
        reviewDto.setRating(3);
        mockMvc.perform(post("/products/{productId}/reviews", 1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reviewDto)))
                .andExpect(status().isCreated());
        Optional<Review> createdReview = reviewRepository.findByText(reviewText);
        assertTrue(createdReview.isPresent());
    }

    @Test
    void newReviewIsNotCreatedWhenProductNotExists() throws Exception {
        ReviewDto reviewDto = new ReviewDto();
        String reviewText = "777_test";
        reviewDto.setText(reviewText);
        reviewDto.setRating(3);
        mockMvc.perform(post("/products/{productId}/reviews", 777)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reviewDto)))
                .andExpect(status().isNotFound());
        Optional<Review> createdReview = reviewRepository.findByText(reviewText);
        assertFalse(createdReview.isPresent());
    }

    @Test
    void reviewShouldNotBeMarkedAsNotActiveIfItIsFromOtherProduct() throws Exception {
        Optional<Review> review = reviewRepository.findByActiveTrueAndId(2L);
        assertTrue(review.isPresent());
        mockMvc.perform(delete("/products/{productId}/reviews/{reviewId}", 33, 0))
                .andExpect(status().isNotFound());
        Optional<Review> removedReview = reviewRepository.findByActiveTrueAndId(2L);
        assertTrue(removedReview.isPresent());
    }

    @Test
    void reviewShouldBeMarkedAsNotActive() throws Exception {
        Optional<Review> review = reviewRepository.findByActiveTrueAndId(0L);
        assertTrue(review.isPresent());
        mockMvc.perform(delete("/products/{productId}/reviews/{reviewId}", 1, 0))
                .andExpect(status().isOk());
        Optional<Review> removedReview = reviewRepository.findByActiveTrueAndId(0L);
        assertFalse(removedReview.isPresent());
    }
}
