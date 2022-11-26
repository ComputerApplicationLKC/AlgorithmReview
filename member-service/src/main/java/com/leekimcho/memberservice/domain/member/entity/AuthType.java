package com.leekimcho.memberservice.domain.member.entity;

import lombok.Getter;

@Getter
public enum AuthType {
    JWT("SOCIAL_KAKAO"), GOOGLE("SOCIAL_GOOGLE");

    private String socialName;

    AuthType(String socialName) {
        this.socialName = socialName;
    }
}