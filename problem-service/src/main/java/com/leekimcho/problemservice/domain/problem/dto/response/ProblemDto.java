package com.leekimcho.problemservice.domain.problem.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leekimcho.problemservice.domain.review.entity.Review;
import com.leekimcho.problemservice.domain.problem.entity.ProblemTag;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

/**
 * 김승진 작성
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ProblemDto {

    private Long id;

    private String title;

    private String link;

    private int step;

    private String content;

    @JsonIgnoreProperties({"problem"})
    private List<Review> reviewList;

    @JsonIgnoreProperties({"problem"})
    private List<ProblemTag> tagList;
}
