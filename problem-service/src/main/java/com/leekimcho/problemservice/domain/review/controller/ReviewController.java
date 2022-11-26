package com.leekimcho.problemservice.domain.review.controller;

import com.leekimcho.problemservice.client.ServiceClient;
import com.leekimcho.problemservice.common.ResponseDto;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.service.ProblemService;
import com.leekimcho.problemservice.domain.review.dto.ReviewRequestDto;
import com.leekimcho.problemservice.domain.review.mapper.ReviewMapper;
import com.leekimcho.problemservice.domain.review.service.ReviewService;
import com.leekimcho.problemservice.utils.auth.AuthCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.leekimcho.problemservice.common.SuccessMessage.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/problem-service/api")
public class ReviewController {

    private final ProblemService problemService;
    private final ReviewService reviewService;

    private final ReviewMapper reviewMapper;
    private final ServiceClient client;

    @AuthCheck
    @PostMapping("/problems/{problemId}/reviews")
    public ResponseEntity<?> saveReview(@PathVariable("problemId") Long problemId, @RequestBody @Valid ReviewRequestDto requestDto) {
        reviewService.registerReview(problemId, requestDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_REGISTER_REVIEW));
    }

    @AuthCheck
    @PutMapping("/problems/{problemId}/reviews/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("problemId") Long problemId,
                                          @PathVariable("reviewId") Long reviewId,
                                          @RequestBody @Valid ReviewRequestDto requestDto) {
        MemberDto member = client.getMemberContext();

        problemService.updateNotificationDate(problemId, member, requestDto.getNotificationDate());
        reviewService.updateReview(reviewId, reviewMapper.toEntity(reviewId, requestDto));
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_UPDATE_REVIEW));
    }

    @AuthCheck
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_REVIEW));
    }
}