package com.leekimcho.reviewservice.entity;

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

    @Column(name = "problem_id",updatable = false)
    private Long problemId;

    @Column
    private String problemTitle;

    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Lob
    @Type(type = "text")
    private String content;

}