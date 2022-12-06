package com.leekimcho.memberservice.domain.member.service;

import com.leekimcho.memberservice.domain.member.controller.MemberController;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    public Member getLoginMember(String email) {
        return memberRepository
                .findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Optional<Member> getLoginMemberByName(String username) {
        return memberRepository
                .findByUsername(username);
    }

}
