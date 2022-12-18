package com.leekimcho.problemservice.domain.problem.repository;

import com.leekimcho.problemservice.domain.problem.entity.ProblemTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 김승진 작성
 */

@Repository
public interface ProblemTagRepository extends JpaRepository<ProblemTag, Long> {
}
