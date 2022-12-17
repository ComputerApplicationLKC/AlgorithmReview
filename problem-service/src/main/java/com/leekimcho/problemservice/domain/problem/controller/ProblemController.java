package com.leekimcho.problemservice.domain.problem.controller;

import com.leekimcho.problemservice.client.ServiceClient;
import com.leekimcho.problemservice.common.ResponseDto;
import com.leekimcho.problemservice.common.SuccessMessage;
import com.leekimcho.problemservice.common.dto.ContextRequest;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemStepUpdateDto;
import com.leekimcho.problemservice.domain.problem.dto.response.ProblemOnlyDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import com.leekimcho.problemservice.domain.problem.mapper.ProblemMapper;
import com.leekimcho.problemservice.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static com.leekimcho.problemservice.common.SuccessMessage.*;
import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/problem-service/problems")
public class ProblemController {

    private final ProblemService problemService;
    private final ProblemMapper problemMapper;
    private final ServiceClient client;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping
    public ResponseEntity<?> getProblemList(@RequestParam(value = "step", defaultValue = "0") int step,
                                            @RequestParam(value = "tag", defaultValue = "") String tagName,
                                            @RequestParam(value = "cursorId", defaultValue = "0") Long cursorId,
                                            @RequestParam(value = "timestamp", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime modifiedDate,
                                            @RequestParam(value = "size", defaultValue = "12") int size){
        List<ProblemOnlyDto> problemList = problemService.getProblemListByStepOrTag(modifiedDate, cursorId, step, tagName, PageRequest.of(0, size))
                .stream().map(problemMapper::toReviewExcludeDto).collect(toList());
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_PROBLEM_LIST, problemList)
        );
    }

    @GetMapping("/{problemId}")
    public ResponseEntity<?> getProblem(@PathVariable Long problemId) {
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_PROBLEM, problemMapper.toDto(problemService.getProblemById(problemId)))
        );
    }

    @GetMapping("/count")
    public ResponseEntity<?> getProblemCount() {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_PROBLEM_COUNT, problemService.getProblemCount()
        ));
    }

    @PostMapping
    public ResponseEntity<?> saveProblem(@RequestBody @Valid ProblemRequestDto requestDto, @RequestHeader String Authorization) {

        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        MemberDto member = circuitbreaker.run(() -> client.getMemberContext(Authorization), throwable -> new MemberDto(1L, "김승진"));
        Problem problem = problemService.registerProblem(problemMapper.toEntity(requestDto, member), requestDto);
        client.getMemberProblem(new ContextRequest(Authorization, problem.getId()));

        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK,
                SuccessMessage.SUCCESS_REGISTER_PROBLEM, problemMapper.toDto(problem)));
    }

    @PutMapping("/{problemId}/step")
    public ResponseEntity<?> updateStep(@PathVariable Long problemId, @RequestBody @Valid ProblemStepUpdateDto updateDto, @RequestHeader String Authorization) {
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        circuitbreaker.run(() -> client.getMemberContext(Authorization), throwable -> new MemberDto(1L, "김승진"));

        Problem problem = problemService.updateStep(problemId, updateDto.getStep());
        client.getMemberProblem(new ContextRequest(Authorization, problem.getId()));

        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_UPDATE_PROBLEM));
    }

    @DeleteMapping("/{problemId}")
    public ResponseEntity<?> deleteProblem(@PathVariable Long problemId, @RequestHeader String Authorization) {

        problemService.deleteProblem(problemId);

        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_PROBLEM));
    }

}
