package com.leekimcho.problemservice.domain.review.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leekimcho.problemservice.client.ServiceClient;
import com.leekimcho.problemservice.common.ResponseDto;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.service.ProblemService;
import com.leekimcho.problemservice.domain.review.dto.ReviewRequestDto;
import com.leekimcho.problemservice.domain.review.mapper.ReviewMapper;
import com.leekimcho.problemservice.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.leekimcho.problemservice.common.SuccessMessage.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/problem-service")
public class ReviewController {

    private final ProblemService problemService;
    private final ReviewService reviewService;

    private final ReviewMapper reviewMapper;
    private final ServiceClient client;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PostMapping("/problems/{problemId}/reviews")
    public ResponseEntity<?> saveReview(@PathVariable("problemId") Long problemId, @RequestBody @Valid ReviewRequestDto requestDto) {
        reviewService.registerReview(problemId, requestDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_REGISTER_REVIEW));
    }

    @PutMapping("/problems/{problemId}/reviews/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("problemId") Long problemId,
                                          @PathVariable("reviewId") Long reviewId,
                                          @RequestBody @Valid ReviewRequestDto requestDto) {
        String get = client.getMemberContext().getBody().replaceAll(System.getProperty("line.separator"), " ");;;
        MemberDto member = gson.fromJson(get, MemberDto.class);

        reviewService.updateReview(reviewId, reviewMapper.toEntity(reviewId, requestDto));
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_UPDATE_REVIEW));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_REVIEW));
    }
}