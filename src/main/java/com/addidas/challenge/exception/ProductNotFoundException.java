package com.addidas.challenge.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private String productId;

    public ProductNotFoundException(String productId) {
        this.productId = productId;
    }

    public ProductNotFoundException(String message, String productId) {
        super(message);
        this.productId = productId;
    }

    public ProductNotFoundException(String message, String productId, Throwable cause) {
        super(message, cause);
        this.productId = productId;
    }

    public ProductNotFoundException(Throwable cause, String productId) {
        super(cause);
        this.productId = productId;
    }
}
