package com.leekimcho.problemservice.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 김승진 작성
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ContextRequest {

    private String email;
    private Long id;
}
