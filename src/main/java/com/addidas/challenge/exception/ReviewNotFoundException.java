package com.addidas.challenge.exception;

import lombok.Getter;

@Getter
public class ReviewNotFoundException extends RuntimeException {
    private String reviewId;

    public ReviewNotFoundException(String reviewId) {
        this.reviewId = reviewId;
    }

    public ReviewNotFoundException(String message, String reviewId) {
        super(message);
        this.reviewId = reviewId;
    }

    public ReviewNotFoundException(String message, String reviewId, Throwable cause) {
        super(message, cause);
        this.reviewId = reviewId;
    }

    public ReviewNotFoundException(Throwable cause, String reviewId) {
        super(cause);
        this.reviewId = reviewId;
    }
}
