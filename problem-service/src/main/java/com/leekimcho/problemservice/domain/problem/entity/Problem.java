package com.leekimcho.problemservice.domain.problem.entity;

import com.leekimcho.problemservice.common.BaseEntity;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.review.entity.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * 김승진 작성
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Entity
@Builder
public class Problem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id", updatable = false)
    private Long id;

    @Embedded
    private MemberDto writer;

    private String title;

    private String link;

    private int step;

    @Builder.Default
    @OrderBy("createdDate desc")
    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProblemTag> tagList = new ArrayList<>();

    public void setReviewAndTagList(Review review, List<ProblemTag> tagList) {
        this.reviewList = singletonList(review);
        this.tagList = tagList;
    }

    public void updateStep(int step) {
        this.step = step;
    }

}