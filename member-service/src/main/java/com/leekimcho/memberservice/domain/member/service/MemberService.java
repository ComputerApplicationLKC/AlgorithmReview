package com.leekimcho.memberservice.domain.member.service;

import com.leekimcho.memberservice.domain.member.dto.MemberDto;

import java.util.List;

public interface MemberService {

    MemberDto findMemberByMemberId(Long MemberId);
    List<MemberDto> findMemberByMemberIds(List<Long> MemberIds);

}