package com.leekimcho.reviewservice.repository;

import com.leekimcho.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM review r r.review_id = :reviewId AND r.member_id = :memberId", nativeQuery = true)
    Optional<Review> findReviewByIdAndMemberId(Long reviewId, Long memberId);
}