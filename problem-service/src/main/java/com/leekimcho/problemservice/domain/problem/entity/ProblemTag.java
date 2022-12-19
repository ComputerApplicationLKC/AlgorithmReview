package com.leekimcho.problemservice.domain.problem.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * 김승진 작성
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class ProblemTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Problem.class)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tag.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public ProblemTag(Problem problem, Tag tag) {
        this.problem = problem;
        this.tag = tag;
    }

    public void setProblemTagNull() {
        this.problem.setTagList(new ArrayList<>());
        this.tag.setProblemListNull(problem.getId());
    }

}