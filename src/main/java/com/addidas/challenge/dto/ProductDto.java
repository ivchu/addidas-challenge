package com.addidas.challenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String id;
    private String title;
    private List<FullReviewDto> reviews;
}
