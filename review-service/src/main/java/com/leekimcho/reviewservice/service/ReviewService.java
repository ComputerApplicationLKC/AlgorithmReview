package com.leekimcho.reviewservice.service;

import com.leekimcho.reviewservice.dto.ReviewRequestDto;
import com.leekimcho.reviewservice.entity.Review;
import com.leekimcho.reviewservice.mapper.ReviewMapper;
import com.leekimcho.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

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