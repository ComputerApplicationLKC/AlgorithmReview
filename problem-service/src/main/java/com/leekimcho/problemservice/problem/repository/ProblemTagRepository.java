package com.leekimcho.problemservice.problem.repository;

import com.leekimcho.problemservice.problem.entity.ProblemTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemTagRepository extends JpaRepository<ProblemTag, Long> {
}
