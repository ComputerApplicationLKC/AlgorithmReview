package com.leekimcho.memberservice.domain.member.entity;

import lombok.Getter;

/**
 * 김승진 작성
 */

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

}