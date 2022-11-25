package com.leekimcho.reviewservice.controller;

import com.leekimcho.reviewservice.dto.ResponseDto;
import com.leekimcho.reviewservice.dto.ReviewRequestDto;
import com.leekimcho.reviewservice.mapper.ReviewMapper;
import com.leekimcho.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.leekimcho.reviewservice.common.message.SuccessMessage.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    private final ReviewMapper reviewMapper;

    @PostMapping("/problems/{problemId}/reviews")
    public ResponseEntity<?> saveReview(@PathVariable("problemId") Long problemId, @RequestBody @Valid ReviewRequestDto requestDto) {

        reviewService.registerReview(problemId, requestDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_REGISTER_REVIEW));
    }

    @PutMapping("/problems/{problemId}/reviews/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("problemId") Long problemId,
                                          @PathVariable("reviewId") Long reviewId,
                                          @RequestBody @Valid ReviewRequestDto requestDto) {
        reviewService.updateReview(reviewId, reviewMapper.toEntity(reviewId, requestDto));
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_UPDATE_REVIEW));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_REVIEW));
    }
}