package com.leekimcho.memberservice.domain.member.service;

import com.leekimcho.memberservice.domain.member.dto.MemberDto;
import com.leekimcho.memberservice.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    MemberDto findMemberByMemberId(Long MemberId);
    List<MemberDto> findMemberByMemberIds(List<Long> MemberIds);
    MemberDto register(MemberDto member);

    Optional<Member> findMemberByEmail(String email);

    void registerAdmin(MemberDto dto, boolean flag);

    List<MemberDto> getAllMembers();
}
