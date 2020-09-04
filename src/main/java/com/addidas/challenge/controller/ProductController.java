package com.addidas.challenge.controller;

import com.addidas.challenge.dto.ProductDto;
import com.addidas.challenge.entity.Product;
import com.addidas.challenge.mapper.ProductMapper;
import com.addidas.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
  OOS just utils controller =)
 */
@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/products")
    public Iterable<ProductDto> getAllProducts() {
        Iterable<Product> all = productRepository.findAll();
        return productMapper.mapToProductsDto(all);
    }
}
