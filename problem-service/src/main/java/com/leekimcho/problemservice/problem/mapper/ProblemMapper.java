package com.leekimcho.problemservice.problem.mapper;

import com.leekimcho.problemservice.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.problem.dto.response.ProblemDto;
import com.leekimcho.problemservice.problem.dto.response.ProblemOnlyDto;
import com.leekimcho.problemservice.problem.entity.Problem;
import com.leekimcho.problemservice.review.dto.ReviewRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProblemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "memberId", source = "memberId")
    @Mapping(target = "tagList", ignore = true)
    @Mapping(target = "reviewList", ignore = true)
    Problem toEntity(ProblemRequestDto requestDto, Long memberId);

    // 리뷰와 함께 업데이트할 때 Problem 으로 매핑
    @Mapping(target = "id", source = "id")
    @Mapping(target = "memberId", source = "memberId")
    @Mapping(target = "tagList", ignore = true)
    @Mapping(target = "link", ignore = true)
    @Mapping(target = "step", ignore = true)
    @Mapping(target = "reviewList", ignore = true)
    @Mapping(target = "title", ignore = true)
    Problem toEntity(Long id, ReviewRequestDto requestDto, Long memberId);

    ProblemDto toDto(Problem problem);

    ProblemOnlyDto toReviewExcludeDto(Problem problem);
}
