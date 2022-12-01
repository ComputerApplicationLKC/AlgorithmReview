package com.leekimcho.problemservice.domain.problem.repository;

import com.leekimcho.problemservice.domain.problem.entity.ProblemTag;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.leekimcho.problemservice.domain.problem.entity.QProblemTag.problemTag;


@Repository
public class TagQueryRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Autowired
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