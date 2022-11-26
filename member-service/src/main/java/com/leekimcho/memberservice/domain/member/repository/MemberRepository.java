package com.leekimcho.memberservice.domain.member.repository;

import com.leekimcho.memberservice.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String memberName);
    boolean existsByEmail(String email);

    Member save(Member member);
}
