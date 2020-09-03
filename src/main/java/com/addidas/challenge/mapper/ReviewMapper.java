package com.addidas.challenge.mapper;

import com.addidas.challenge.dto.ReviewDto;
import com.addidas.challenge.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto mapToReviewDto(Review  review);
}
