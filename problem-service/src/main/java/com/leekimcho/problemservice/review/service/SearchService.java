package com.leekimcho.problemservice.review.service;

import com.leekimcho.problemservice.review.entity.Review;
import com.leekimcho.problemservice.review.repository.ReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final ReviewQueryRepository reviewQueryRepository;

    public List<Review> search(String keyword, Long reviewId, int size) {
        return reviewQueryRepository.search(keyword, reviewId, size);
    }

}