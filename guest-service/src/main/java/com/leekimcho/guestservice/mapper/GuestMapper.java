package com.leekimcho.guestservice.mapper;

import com.leekimcho.guestservice.dto.GuestRequestDto;
import com.leekimcho.guestservice.dto.GuestResponseDto;
import com.leekimcho.guestservice.entity.GuestBook;
import org.springframework.stereotype.Component;

/**
 * 김승진 작성
 */

@Component
public class GuestMapper {

    public GuestBook toEntity(GuestRequestDto guestRequestDto) {
        return GuestBook.builder()
                .content(guestRequestDto.getContent())
                .memberId(guestRequestDto.getMemberId())
                .nickname(guestRequestDto.getNickname())
                .build();
    }

    public GuestResponseDto toDto(GuestBook guestBook) {
        return GuestResponseDto.builder()
                .id(guestBook.getId())
                .content(guestBook.getContent())
                .nickname(guestBook.getNickname())
                .createdDate(guestBook.getCreatedDate().toLocalDate())
                .build();
    }

}
