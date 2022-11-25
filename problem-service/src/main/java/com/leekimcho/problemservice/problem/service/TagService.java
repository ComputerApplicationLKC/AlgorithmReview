package com.leekimcho.problemservice.problem.service;

import com.leekimcho.problemservice.problem.dto.response.TagDto;
import com.leekimcho.problemservice.problem.repository.TagQueryRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagService {

    private final TagQueryRepository tagQueryRepository;

    @Cacheable(value = "tagCache")
    public List<TagDto> getAllTagList() {
        List<TagDto> tagList = new ArrayList<>();
        List<Tuple> list = tagQueryRepository.findAllTagList();
        log.info("Not Cached");
        list.forEach(tuple -> tagList.add(TagDto.builder().tagName(tuple.get(0, String.class)).count(tuple.get(1, Long.class)).build()));
        return tagList;
    }

}
