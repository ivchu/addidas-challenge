package com.addidas.challenge.service;

import com.addidas.challenge.entity.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long productId);
}
