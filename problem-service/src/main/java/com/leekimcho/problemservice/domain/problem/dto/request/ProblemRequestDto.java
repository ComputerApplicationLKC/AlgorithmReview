package com.leekimcho.problemservice.domain.problem.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * 김승진 작성
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Builder
public class ProblemRequestDto {

    @NotBlank
    private String title;

    @NotNull
    private String link;

    @Builder.Default
    private int step = 1;

    @NotNull
    private ArrayList<String> tagList;

    @NotBlank
    private String content;

}