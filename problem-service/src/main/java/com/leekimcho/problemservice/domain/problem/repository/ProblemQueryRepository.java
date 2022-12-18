package com.leekimcho.problemservice.domain.problem.repository;

import com.leekimcho.problemservice.domain.problem.entity.Problem;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

import static com.leekimcho.problemservice.domain.problem.entity.QProblem.problem;
import static com.leekimcho.problemservice.domain.problem.entity.QProblemTag.problemTag;

/**
 * 김승진 작성
 */


@Repository
public class ProblemQueryRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public ProblemQueryRepository(JPAQueryFactory queryFactory) {
        super(Problem.class);
        this.queryFactory = queryFactory;
    }

    public List<Problem> findAllByTag(LocalDateTime modifiedDate, Long problemId, String tagName, int size) {
        return queryFactory
                .selectFrom(problem)
                .join(problemTag).on(problemTag.problem.eq(problem))
                .where(
                        beforeModifiedDateAndNotProblemId(modifiedDate, problemId),
                        problemTag.tag.tagName.eq(tagName)
                )
                .orderBy(problem.id.desc())
                .limit(size)
                .fetch();
    }

    private BooleanExpression beforeModifiedDateAndNotProblemId(LocalDateTime modifiedDate, Long problemId) {
        // id < 파라미터를 첫 페이지에선 사용하지 않기 위한 동적 쿼리
        if (modifiedDate == null) {
            return null; // BooleanExpression 자리에 null 이 반환되면 조건문에서 자동으로 제거
        }
        if (problemId.equals(0L)) {
            return problem.modifiedDate.before(modifiedDate);
        }
        return problem.modifiedDate.before(modifiedDate).and(problem.id.notIn(problemId));
    }

}