package com.leekimcho.problemservice.review.mapper;

import com.leekimcho.problemservice.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.problem.entity.Problem;
import com.leekimcho.problemservice.review.dto.ReviewRequestDto;
import com.leekimcho.problemservice.review.dto.SearchDto;
import com.leekimcho.problemservice.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toEntity(Long reviewId, ReviewRequestDto requestDto);

    Review toEntity(Problem problem, ProblemRequestDto requestDto);

    @Mapping(target = "id", source="id")
    SearchDto toSearchDto(Review review);

}
