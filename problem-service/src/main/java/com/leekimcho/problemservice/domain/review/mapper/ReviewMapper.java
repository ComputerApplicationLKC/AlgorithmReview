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

    @Mapping(target = "problem", source = "problem")
    @Mapping(target = "id", ignore = true)
    Review toEntity(Problem problem, ProblemRequestDto requestDto);

    @Mapping(target = "problem", source = "problem")
    @Mapping(target = "id", ignore = true)
    Review toEntity(Problem problem, ReviewRequestDto requestDto);

    @Mapping(target = "problem", ignore = true)
    @Mapping(target = "id", source = "id")
    Review toEntity(Long id, ReviewRequestDto requestDto);

    @Mapping(target = "id", source="id")
    @Mapping(target = "problemId", source="problem.id")
    @Mapping(target = "title", source="problem.title")
    @Mapping(target = "step", source="problem.step")
    @Mapping(target = "link", source="problem.link")
    SearchDto toSearchDto(Review review);

}
