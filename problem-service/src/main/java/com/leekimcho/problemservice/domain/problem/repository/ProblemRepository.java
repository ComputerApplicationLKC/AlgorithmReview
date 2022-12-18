package com.leekimcho.problemservice.domain.problem.repository;

import com.leekimcho.problemservice.domain.problem.entity.Problem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 김승진 작성
 */

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    List<Problem> findByOrderByModifiedDateDesc(Pageable pageable); // 최신 업데이트순

    // 업데이트 날짜가 같은 경우를 고려해 (포함되지 않아야 할) cursorId 추가
    List<Problem> findByModifiedDateBeforeAndIdNotOrderByModifiedDateDesc(LocalDateTime modifiedDate, Long id, Pageable pageable); // 커서 기반 페이징 (업데이트순)

    @Query(value = "SELECT p FROM Problem p WHERE p.id = :id and p.writer.memberId = :memberId")
    Optional<Problem> findProblemByIdAndMemberId(Long id, Long memberId);

    void deleteProblemById(Long id);

    List<Problem> findAllByStepOrderByModifiedDateDesc(int step, Pageable pageable);

    List<Problem> findByIdLessThanAndStepOrderByIdDesc(Long id, int step, Pageable pageable); // 커서 기반 페이징

}