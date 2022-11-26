package com.leekimcho.problemservice.domain.problem.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ProblemUpdateDto {

    @NotBlank
    private String title;

    @NotNull
    private String link;

    @NotNull
    private ArrayList<String> tagList;

    @NotNull
    private LocalDate notificationDate;

}