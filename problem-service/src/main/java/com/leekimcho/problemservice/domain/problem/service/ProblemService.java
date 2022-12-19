package com.leekimcho.problemservice.domain.problem.service;

import com.leekimcho.problemservice.common.advice.exception.EntityNotFoundException;
import com.leekimcho.problemservice.domain.problem.dto.request.ProblemRequestDto;
import com.leekimcho.problemservice.domain.problem.entity.Problem;
import com.leekimcho.problemservice.domain.problem.entity.ProblemTag;
import com.leekimcho.problemservice.domain.problem.entity.Tag;
import com.leekimcho.problemservice.domain.problem.repository.ProblemQueryRepository;
import com.leekimcho.problemservice.domain.problem.repository.ProblemRepository;
import com.leekimcho.problemservice.domain.problem.repository.ProblemTagRepository;
import com.leekimcho.problemservice.domain.problem.repository.TagRepository;
import com.leekimcho.problemservice.domain.review.entity.Review;
import com.leekimcho.problemservice.domain.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 김승진 작성
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final TagRepository tagRepository;
    private final ProblemQueryRepository problemQueryRepository;
    private final ReviewMapper reviewMapper;
    private final ProblemTagRepository problemTagRepository;

    @Transactional(readOnly = true)
    public List<Problem> getProblemListByStepOrTag(LocalDateTime modifiedDate, Long cursorId, int step, String tagName, Pageable page) {
        if (step == 0 && tagName.isBlank()) {
            return getProblemList(modifiedDate, cursorId, page);
        }
        else if (step == 0) {
            return getProblemListByTagName(modifiedDate, tagName, cursorId, page);
        }
        else {
            return getProblemList(modifiedDate, cursorId, page);
        }
    }

    @Transactional(readOnly = true)
    public Problem getProblemById(Long problemId) {
        return problemRepository.findById(problemId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public long getProblemCount() {
        return problemRepository.count();
    }

    @Transactional
    public Problem registerProblem(Problem problem, ProblemRequestDto registerDto) {
        Review review = reviewMapper.toEntity(problem, registerDto);

        List<ProblemTag> tagList = registerDto.getTagList().stream()
                .map(tagName -> tagRepository.findByTagName(tagName)
                        .map(
                                tag -> new ProblemTag(problem, tag)).orElseGet(
                                () -> new ProblemTag(problem, new Tag(tagName))
                        )).collect(Collectors.toSet()).stream().collect(Collectors.toList());

        problem.setReviewAndTagList(review, tagList);
        problemRepository.save(problem);

        return problem;
    }

    @Transactional
    public Problem updateStep(Long problemId, int step) {
        Problem updateProblem = findById(problemId);
        updateProblem.updateStep(step);
        Problem problem = problemRepository.save(updateProblem);
        return problem;
    }

    @Transactional
    public void deleteProblem(Long problemId) {
        List<ProblemTag> problemTagList = problemTagRepository.findByProblem(problemRepository.findById(problemId).orElseThrow(EntityNotFoundException::new));
        problemTagList.stream().forEach(problemTag -> {
            problemTag.setProblemTagNull();
            problemTagRepository.delete(problemTag);
        });
        problemRepository.deleteById(problemId);
    }

    public List<Problem> getProblemList(LocalDateTime modifiedDate, Long cursorId, Pageable page) {
        if (modifiedDate == null) {
            return problemRepository.findByOrderByModifiedDateDesc(page);
        }
        return problemRepository.findByModifiedDateBeforeAndIdNotOrderByModifiedDateDesc(modifiedDate, cursorId, page);
    }

    public List<Problem> getProblemListByStep(int step, Long cursorId, Pageable page) {
        return cursorId.equals(0L) ?
                problemRepository.findAllByStepOrderByModifiedDateDesc(step, page) :
                problemRepository.findByIdLessThanAndStepOrderByIdDesc(cursorId, step, page); // 커서기반 페이징
    }

    public List<Problem> getProblemListByTagName(LocalDateTime modifiedDate, String tagName, Long cursorId, Pageable page) {
        if (tagName == null) {
            return getProblemList(modifiedDate, cursorId, page);
        }
        return problemQueryRepository.findAllByTag(modifiedDate, cursorId, tagName, page.getPageSize());
    }

    private Problem findById(Long problemId) {
        return problemRepository.findById(problemId)
                .orElseThrow(EntityNotFoundException::new);
    }
}