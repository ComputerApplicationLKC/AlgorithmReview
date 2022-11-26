package com.leekimcho.problemservice.domain.problem.controller;

import com.leekimcho.problemservice.common.ResponseDto;
import com.leekimcho.problemservice.domain.problem.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.leekimcho.problemservice.common.SuccessMessage.SUCCESS_GET_TAG_LIST;

@RequiredArgsConstructor
@RestController
@RequestMapping("/problem-service/api")
public class TagController {
    private final TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<?> getProblemListByTag() {
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_TAG_LIST, tagService.getAllTagList())
        );
    }

}
