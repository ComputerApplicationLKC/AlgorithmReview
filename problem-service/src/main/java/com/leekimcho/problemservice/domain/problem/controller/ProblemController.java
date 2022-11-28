package com.leekimcho.problemservice.domain.problem.controller;

import com.leekimcho.problemservice.client.ServiceClient;
import com.leekimcho.problemservice.common.ResponseDto;
import com.leekimcho.problemservice.common.SuccessMessage;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemNotificationUpdateDto;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemStepUpdateDto;
import com.leekimcho.problemservice.domain.problem.dto.response.ProblemOnlyDto;
import com.leekimcho.problemservice.domain.problem.mapper.ProblemMapper;
import com.leekimcho.problemservice.domain.problem.service.ProblemService;
import com.leekimcho.problemservice.utils.auth.AuthCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    public ResponseEntity<?> getProblemList(@RequestParam(value = "step", defaultValue = "0") int step,
                                            @RequestParam(value = "tag", defaultValue = "") String tagName,
                                            @RequestParam(value = "cursorId", defaultValue = "0") Long cursorId,
                                            @RequestParam(value = "timestamp", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime modifiedDate,
                                            @RequestParam(value = "size", defaultValue = "12") int size){
        // PageRequest.of()의 첫 번째 파라미터는 무조건 0으로, 즉 최초의 페이지로 처리를 해야 한다.
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

    @AuthCheck
    @PostMapping
    public ResponseEntity<?> saveProblem(@RequestBody @Valid ProblemRequestDto requestDto) {
        MemberDto member = client.getMemberContext();

        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK,
                SuccessMessage.SUCCESS_REGISTER_PROBLEM,
                problemService.registerProblem(problemMapper.toEntity(requestDto, member), requestDto)
        ));
    }

    @AuthCheck
    @PutMapping("/{problemId}/step")
    public ResponseEntity<?> updateStep(@PathVariable Long problemId, @RequestBody @Valid ProblemStepUpdateDto updateDto) {
        MemberDto member = client.getMemberContext();

        problemService.updateStep(problemId, member, updateDto.getStep());

        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_UPDATE_PROBLEM));
    }

    @AuthCheck
    @PutMapping("/{problemId}/notification")
    public ResponseEntity<?> updateNotificationDate(@PathVariable Long problemId, @RequestBody @Valid ProblemNotificationUpdateDto updateDto) {
        MemberDto member = client.getMemberContext();

        problemService.updateNotificationDate(problemId, member, updateDto.getNotificationDate());

        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_UPDATE_PROBLEM));
    }

    @DeleteMapping("/{problemId}")
    public ResponseEntity<?> deleteProblem(@PathVariable Long problemId) {
        MemberDto member = client.getMemberContext();

        problemService.deleteProblem(problemId, member);

        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_PROBLEM));
    }

}
