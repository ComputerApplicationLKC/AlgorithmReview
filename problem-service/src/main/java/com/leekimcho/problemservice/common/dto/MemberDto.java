package com.leekimcho.problemservice.common.dto;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class MemberDto {
    private Long id;
    private String email;
    private String nickname;
}
