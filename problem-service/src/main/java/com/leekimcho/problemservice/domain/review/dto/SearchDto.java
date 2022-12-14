package com.leekimcho.problemservice.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 김승진 작성
 */

@Getter @Setter @Builder
public class SearchDto {

    private Long id;

    private Long problemId;

    private String title;

    private int step;

    private String link;

    private LocalDate createdDate;

    private String content;

}