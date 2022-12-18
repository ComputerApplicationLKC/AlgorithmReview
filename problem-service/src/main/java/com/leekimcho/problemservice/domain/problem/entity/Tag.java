package com.leekimcho.problemservice.domain.problem.entity;

import lombok.*;

import javax.persistence.*;

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

    public Tag(String tagName) {
        this.tagName = tagName;
    }

}