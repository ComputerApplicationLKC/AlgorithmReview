package com.leekimcho.problemservice.domain.problem.mapper;

import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.domain.problem.dto.response.ProblemDto;
import com.leekimcho.problemservice.domain.problem.dto.response.ProblemOnlyDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import org.springframework.stereotype.Component;


@Component
public class ProblemMapper {

    public Problem toEntity(ProblemRequestDto requestDto, MemberDto member) {
        return Problem.builder()
                .link(requestDto.getLink())
                .step(requestDto.getStep())
                .title(requestDto.getTitle())
                .writer(member)
                .build();
    }

    public ProblemDto toDto(Problem problem) {
        return ProblemDto.builder()
                .step(problem.getStep())
                .title(problem.getTitle())
                .id(problem.getId())
                .link(problem.getLink())
                .reviewList(problem.getReviewList())
                .tagList(problem.getTagList())
                .build();
    }

    public ProblemOnlyDto toReviewExcludeDto(Problem problem) {
        return ProblemOnlyDto.builder()
                .id(problem.getId())
                .tagList(problem.getTagList())
                .title(problem.getTitle())
                .step(problem.getStep())
                .link(problem.getLink())
                .modifiedDate(problem.getModifiedDate())
                .build();
    }

}
