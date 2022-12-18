package com.leekimcho.memberservice.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 김승진 작성
 */

@Builder
@Getter
public class LoginSuccessDto {
    private String nickname;
    private String email;
}
