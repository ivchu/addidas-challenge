package com.addidas.challenge.mapper;

import com.addidas.challenge.dto.ReviewDto;
import com.addidas.challenge.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FullReviewMapper {
    ReviewDto mapToReviewDto(Review review);

    @Mapping(target = "product", ignore = true)
    Review mapReviewDtoToEntity(ReviewDto reviewDto);
}
