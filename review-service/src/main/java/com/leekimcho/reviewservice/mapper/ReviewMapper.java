package com.leekimcho.reviewservice.mapper;

import com.leekimcho.reviewservice.dto.ReviewRequestDto;
import com.leekimcho.reviewservice.dto.SearchDto;
import com.leekimcho.reviewservice.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toEntity(Long id, ReviewRequestDto requestDto);

    @Mapping(target = "id", source="id")
    SearchDto toSearchDto(Review review);

}
