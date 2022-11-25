package com.leekimcho.problemservice.problem.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leekimcho.problemservice.problem.entity.ProblemTag;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ProblemOnlyDto {

    private Long id;

    private String title;

    private String link;

    private int step;

    private LocalDate notificationDate;

    private LocalDateTime modifiedDate;

    @JsonIgnoreProperties({"problem"})
    private List<ProblemTag> tagList;
}