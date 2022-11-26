package com.leekimcho.problemservice.domain.review.entity;

import com.leekimcho.problemservice.common.BaseEntity;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "problem_id", updatable = false)
    private Problem problem;

    @Embedded
    private MemberDto member;

    @Lob
    @Type(type = "text")
    private String content;

}