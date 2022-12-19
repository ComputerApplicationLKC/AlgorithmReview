package com.leekimcho.problemservice.domain.problem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 김승진 작성
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(unique = true)
    private String tagName;

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<ProblemTag> problemList = new ArrayList<>();

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public void setProblemListNull(Long problemId) {
        problemList = problemList.stream()
                .filter(i -> i.getProblem().getId() != problemId)
                .collect(Collectors.toList());
    }

}