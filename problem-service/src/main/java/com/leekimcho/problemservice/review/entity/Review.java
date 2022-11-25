package com.leekimcho.problemservice.review.entity;

import com.leekimcho.problemservice.common.BaseEntity;
import com.leekimcho.problemservice.problem.entity.Problem;
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

    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Lob
    @Type(type = "text")
    private String content;

}