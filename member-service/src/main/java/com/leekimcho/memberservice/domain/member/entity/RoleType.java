package com.leekimcho.memberservice.domain.member.entity;

import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }
}