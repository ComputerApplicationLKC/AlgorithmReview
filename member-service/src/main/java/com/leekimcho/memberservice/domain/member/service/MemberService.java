package com.leekimcho.memberservice.domain.member.service;

import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

}
