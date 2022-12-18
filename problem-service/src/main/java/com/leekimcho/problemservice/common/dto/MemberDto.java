package com.leekimcho.problemservice.common.dto;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * 김승진 작성
 */

@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long memberId;
    private String username;
}
