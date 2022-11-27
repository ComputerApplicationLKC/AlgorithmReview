package com.leekimcho.memberservice.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class JwtPayload {

    private Long id;

    private String email;
    private String accessToken;
    private Date accessTokenExpiredDate;
    private String refreshToken;

    @Builder
    public JwtPayload(String accessToken, String refreshToken, Date accessTokenExpiredDate) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiredDate = accessTokenExpiredDate;
    }

    @Builder
    public JwtPayload(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
