package com.leekimcho.problemservice.problem.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProblemStepUpdateDto {

    @NotNull
    private int step;

}