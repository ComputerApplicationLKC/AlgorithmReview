package com.leekimcho.problemservice.domain.problem.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * 김승진 작성
 */

@Getter
public class ProblemStepUpdateDto {

    @NotNull
    private int step;

}