package com.leekimcho.memberservice.domain.member.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class GoogleTokenDto {

    @NotBlank
    private String accessToken;

}