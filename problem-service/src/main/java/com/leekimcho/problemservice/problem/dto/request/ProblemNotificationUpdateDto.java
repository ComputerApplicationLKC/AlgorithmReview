package com.leekimcho.problemservice.problem.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class ProblemNotificationUpdateDto {

    @NotNull
    private LocalDate notificationDate;

}