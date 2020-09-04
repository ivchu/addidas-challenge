package com.addidas.challenge.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FullReviewDto {
    private String id;
    private String text;
    private Integer rating;
    private Boolean active;
    private Date date;
}
