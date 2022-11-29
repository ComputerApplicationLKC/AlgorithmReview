package com.leekimcho.problemservice.domain.review.controller;

import com.leekimcho.problemservice.common.ResponseDto;
import com.leekimcho.problemservice.domain.review.dto.SearchDto;
import com.leekimcho.problemservice.domain.review.mapper.ReviewMapper;
import com.leekimcho.problemservice.domain.review.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.leekimcho.problemservice.common.SuccessMessage.SUCCESS_GET_SEARCH_LIST;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/problem-service")
public class SearchController {

    private final SearchService searchService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public ResponseEntity<?> search(@RequestParam(value = "search") String name,
                                    @RequestParam(defaultValue = "0") Long cursorId,
                                    @RequestParam(defaultValue = "10") int size) {
        List<SearchDto> searchList = searchService.search(name, cursorId, size).stream()
                .map(reviewMapper::toSearchDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_GET_SEARCH_LIST, searchList));
    }
}
