package com.leekimcho.problemservice.domain.review.mapper;

import com.leekimcho.problemservice.domain.review.entity.Review;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import com.leekimcho.problemservice.domain.review.dto.ReviewRequestDto;
import com.leekimcho.problemservice.domain.review.dto.SearchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toEntity(Long reviewId, ReviewRequestDto requestDto);

    Review toEntity(Problem problem, ProblemRequestDto requestDto);

    @Mapping(target = "id", source="id")
    SearchDto toSearchDto(Review review);

}
