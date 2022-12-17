package com.leekimcho.guestservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @Builder
public class GuestResponseDto {

    private Long id;

    private String nickname;

    private String content;

    private LocalDate createdDate;

}
