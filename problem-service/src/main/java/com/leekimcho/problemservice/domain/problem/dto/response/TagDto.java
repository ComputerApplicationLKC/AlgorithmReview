package com.leekimcho.problemservice.domain.problem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 김승진 작성
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TagDto {
    private String tagName;
    private Long count;
}