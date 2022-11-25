package com.leekimcho.problemservice.problem.repository;

import com.leekimcho.problemservice.problem.entity.ProblemTag;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.leekimcho.problemservice.problem.entity.QProblemTag.problemTag;

@Repository
public class TagQueryRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public TagQueryRepository(JPAQueryFactory queryFactory) {
        super(ProblemTag.class);
        this.queryFactory = queryFactory;
    }

    public List<Tuple> findAllTagList() {
        return queryFactory
                .from(problemTag)
                .groupBy(problemTag.tag)
                .select(problemTag.tag.tagName, problemTag.tag.count())
                .fetch();
    }
}