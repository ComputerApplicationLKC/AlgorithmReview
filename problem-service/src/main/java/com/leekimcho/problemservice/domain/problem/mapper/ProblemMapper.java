package com.leekimcho.problemservice.domain.problem.mapper;

import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.dto.response.ProblemDto;
import com.leekimcho.problemservice.domain.problem.dto.response.ProblemOnlyDto;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import com.leekimcho.problemservice.domain.review.dto.ReviewRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProblemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tagList", ignore = true)
    @Mapping(target = "reviewList", ignore = true)
    Problem toEntity(ProblemRequestDto requestDto, MemberDto member);

    // 리뷰와 함께 업데이트할 때 Problem 으로 매핑
    @Mapping(target = "id", source = "id")
    @Mapping(target = "writer", source = "member")
    @Mapping(target = "tagList", ignore = true)
    @Mapping(target = "link", ignore = true)
    @Mapping(target = "step", ignore = true)
    @Mapping(target = "reviewList", ignore = true)
    @Mapping(target = "title", ignore = true)
    Problem toEntity(Long id, ReviewRequestDto requestDto, MemberDto member);

    ProblemDto toDto(Problem problem);

    ProblemOnlyDto toReviewExcludeDto(Problem problem);
}
