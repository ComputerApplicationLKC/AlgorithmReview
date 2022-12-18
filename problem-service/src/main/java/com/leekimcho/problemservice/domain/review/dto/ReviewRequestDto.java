package com.leekimcho.problemservice.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 김승진 작성
 */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {

    @NotBlank
    private String content;

}