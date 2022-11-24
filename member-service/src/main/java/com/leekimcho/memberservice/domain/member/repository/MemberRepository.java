package com.leekimcho.memberservice.domain.member.repository;

import com.leekimcho.memberservice.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String Membername);
    boolean existsByEmail(String email);
}
