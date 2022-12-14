package com.leekimcho.problemservice.domain.review.mapper;

import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import com.leekimcho.problemservice.domain.review.dto.ReviewRequestDto;
import com.leekimcho.problemservice.domain.review.dto.SearchDto;
import com.leekimcho.problemservice.domain.review.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review toEntity(Problem problem, ProblemRequestDto requestDto) {
        return Review.builder()
                .content(requestDto.getContent())
                .problem(problem)
                .member(problem.getWriter())
                .build();
    }

    public Review toEntity(Long id, ReviewRequestDto requestDto, MemberDto member) {
        return Review.builder()
                .id(id)
                .content(requestDto.getContent())
                .member(member)
                .build();
    }

    public SearchDto toSearchDto(Review review) {
        return SearchDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .problemId(review.getProblem().getId())
                .createdDate(review.getCreatedDate().toLocalDate())
                .build();
    }

}
