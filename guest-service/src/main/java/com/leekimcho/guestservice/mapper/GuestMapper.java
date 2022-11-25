package com.leekimcho.guestservice.mapper;

import com.leekimcho.guestservice.dto.GuestRequestDto;
import com.leekimcho.guestservice.dto.GuestResponseDto;
import com.leekimcho.guestservice.entity.GuestBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    @Mapping(target = "id", ignore = true)
    GuestBook toEntity(GuestRequestDto guestRequestDto);

    GuestResponseDto toDto(GuestBook guestBook);

}
