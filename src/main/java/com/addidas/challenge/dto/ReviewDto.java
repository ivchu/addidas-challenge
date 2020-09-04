package com.addidas.challenge.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ReviewDto {
    private String id;
    private String text;
    @Min(1)
    @Max(5)
    private Integer rating;
}
