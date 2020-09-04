package com.addidas.challenge.service.impl;

import com.addidas.challenge.entity.Product;
import com.addidas.challenge.repository.ProductRepository;
import com.addidas.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }
}
