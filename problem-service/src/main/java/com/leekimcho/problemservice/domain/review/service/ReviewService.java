package com.leekimcho.problemservice.domain.review.service;

import com.leekimcho.problemservice.common.advice.exception.EntityNotFoundException;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.repository.ProblemRepository;
import com.leekimcho.problemservice.domain.review.dto.ReviewRequestDto;
import com.leekimcho.problemservice.domain.review.entity.Review;
import com.leekimcho.problemservice.domain.review.mapper.ReviewMapper;
import com.leekimcho.problemservice.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    private final ReviewRepository reviewRepository;

    private final ProblemRepository problemRepository;

    @Transactional
    public void registerReview(Long problemId, ReviewRequestDto registerDto, MemberDto member) {
        reviewRepository.save(reviewMapper.toEntity(problemRepository.findById(problemId).get(), registerDto, member));
    }

    @Transactional
    public void updateReview(Long reviewId, Review review) {
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId, MemberDto member) {
        reviewRepository.findReviewByIdAndMemberId(reviewId, member.getMemberId()).ifPresent(
                review -> reviewRepository.delete(review)
        );
    }

    @Transactional(readOnly = true)
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);
    }

}