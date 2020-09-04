package com.addidas.challenge.mapper;

import com.addidas.challenge.dto.FullReviewDto;
import com.addidas.challenge.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FullReviewMapper {
    FullReviewDto mapToReviewDto(Review review);

    List<FullReviewDto> mapToReviewDtos(List<Review> review);

    @Mapping(target = "product", ignore = true)
    Review mapReviewDtoToEntity(FullReviewDto fullReviewDto);
}
