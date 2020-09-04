package com.addidas.challenge.controller.advice;

import com.addidas.challenge.exception.ProductNotFoundException;
import com.addidas.challenge.exception.ReviewNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

    @ExceptionHandler(value = { ProductNotFoundException.class })
    ResponseEntity<String> handleProductNotFound(ProductNotFoundException exception){
        LOGGER.error("Product with id {} was not found", exception.getProductId(), exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @ExceptionHandler(value = { ReviewNotFoundException.class })
    ResponseEntity<String> handleReviewNotFound(ReviewNotFoundException exception){
        LOGGER.error("Review with id {} was not found", exception.getReviewId(), exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getInvalidFields(ex), headers, status);

    }

    private Map<String, String> getInvalidFields(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors().stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
    }
}
