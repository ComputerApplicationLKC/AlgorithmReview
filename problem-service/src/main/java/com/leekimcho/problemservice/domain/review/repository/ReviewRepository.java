package com.leekimcho.problemservice.domain.review.repository;

import com.leekimcho.problemservice.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 김승진 작성
 */

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}