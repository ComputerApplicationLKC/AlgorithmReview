package com.leekimcho.problemservice.domain.problem.repository;

import com.leekimcho.problemservice.domain.problem.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 김승진 작성
 */

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);
}