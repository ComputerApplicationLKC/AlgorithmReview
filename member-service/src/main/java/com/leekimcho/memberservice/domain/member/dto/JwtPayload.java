package com.leekimcho.memberservice.domain.member.dto;

import com.leekimcho.memberservice.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class JwtPayload {

    private Long id;
    private String email;

    @Builder
    public JwtPayload(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public JwtPayload(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
    }

}
