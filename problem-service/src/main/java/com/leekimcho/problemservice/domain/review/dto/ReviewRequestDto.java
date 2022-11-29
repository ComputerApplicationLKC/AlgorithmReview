package com.leekimcho.problemservice.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Getter
public class ReviewRequestDto {

    @NotBlank
    private String content;

}