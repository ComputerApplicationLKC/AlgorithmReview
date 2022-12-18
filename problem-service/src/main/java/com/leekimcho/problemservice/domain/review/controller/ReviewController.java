package com.leekimcho.problemservice.domain.review.controller;

import com.leekimcho.problemservice.client.ServiceClient;
import com.leekimcho.problemservice.common.ResponseDto;
import com.leekimcho.problemservice.common.dto.ContextRequest;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.common.dto.MemberIdDto;
import com.leekimcho.problemservice.domain.review.dto.ReviewRequestDto;
import com.leekimcho.problemservice.domain.review.mapper.ReviewMapper;
import com.leekimcho.problemservice.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.leekimcho.problemservice.common.SuccessMessage.*;

/**
 * 김승진 작성
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/problem-service")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final ServiceClient client;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @PostMapping("/problems/{problemId}/reviews")
    public ResponseEntity<?> saveReview(@PathVariable("problemId") Long problemId, @RequestBody @Valid ReviewRequestDto requestDto, @RequestHeader String Authorization) {
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        MemberIdDto member = circuitbreaker.run(() -> client.getMemberProblem(new ContextRequest(Authorization, problemId)), throwable -> new MemberIdDto(1L, 1L, "김승진"));

        reviewService.registerReview(problemId, requestDto, new MemberDto(member.getMemberId(), member.getUsername()));

        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_REGISTER_REVIEW));
    }

    @PutMapping("/problems/{problemId}/reviews/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("problemId") Long problemId,
                                          @PathVariable("reviewId") Long reviewId,
                                          @RequestBody @Valid ReviewRequestDto requestDto,
                                          @RequestHeader String Authorization) {
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        MemberIdDto member = circuitbreaker.run(() -> client.getMemberProblem(new ContextRequest(Authorization, problemId)), throwable -> new MemberIdDto(1L, 1L, "김승진"));

        reviewService.updateReview(reviewId, reviewMapper.toEntity(reviewId, requestDto, new MemberDto(member.getMemberId(), member.getUsername())));
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_UPDATE_REVIEW));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId, @RequestHeader String Authorization) {
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        MemberDto member = circuitbreaker.run(() -> client.getMemberContext(Authorization), throwable -> new MemberDto(1L, "김승진"));

        reviewService.deleteReview(reviewId, member);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_REVIEW));
    }
}