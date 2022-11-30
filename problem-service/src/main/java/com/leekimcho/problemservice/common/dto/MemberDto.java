package com.leekimcho.problemservice.common.dto;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class MemberDto {
    private Long memberId;
    private String nickname;
}
