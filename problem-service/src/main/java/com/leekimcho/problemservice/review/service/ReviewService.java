package com.leekimcho.problemservice.review.service;

import com.leekimcho.problemservice.common.advice.exception.EntityNotFoundException;
import com.leekimcho.problemservice.review.dto.ReviewRequestDto;
import com.leekimcho.problemservice.review.entity.Review;
import com.leekimcho.problemservice.review.mapper.ReviewMapper;
import com.leekimcho.problemservice.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    private final ReviewRepository reviewRepository;

    @Transactional
    public void registerReview(Long problemId, ReviewRequestDto registerDto) {
        reviewRepository.save(reviewMapper.toEntity(problemId, registerDto));
    }

    @Transactional
    public void updateReview(Long reviewId, Review review) {
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Transactional(readOnly = true)
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);
    }

}