package com.leekimcho.guestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 김승진 작성
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberIdDto {
    private Long id;
    private Long memberId;
    private String username;
}