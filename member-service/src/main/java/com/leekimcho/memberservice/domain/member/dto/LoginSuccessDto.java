package com.leekimcho.memberservice.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginSuccessDto {
    private String nickname;
    private String email;
}
