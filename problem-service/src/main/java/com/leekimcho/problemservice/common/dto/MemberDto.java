package com.leekimcho.problemservice.common.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class MemberDto {
    @Column(name = "member_id")
    private Long memberId;
    private String nickname;
}
