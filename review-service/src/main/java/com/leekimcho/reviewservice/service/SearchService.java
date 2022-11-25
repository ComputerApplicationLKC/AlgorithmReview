package com.leekimcho.reviewservice.service;

import com.leekimcho.reviewservice.entity.Review;
import com.leekimcho.reviewservice.repository.ReviewQueryRepository;
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