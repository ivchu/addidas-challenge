package com.addidas.challenge.mapper;

import com.addidas.challenge.dto.ReviewDto;
import com.addidas.challenge.dto.ShortReviewDto;
import com.addidas.challenge.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ShortReviewDto mapToReviewDto(Review  review);

    List<ReviewDto> mapToReviewDtos(List<Review> review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "product", ignore = true)
    Review createReview(ReviewDto reviewDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateReview(ReviewDto reviewDto, @MappingTarget Review review);
}
