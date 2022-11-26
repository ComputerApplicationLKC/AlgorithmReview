package com.leekimcho.memberservice.global.utils.auth;


import com.leekimcho.memberservice.domain.member.entity.Member;

public class MemberContext {
    public static ThreadLocal<Member> currentMember = new ThreadLocal<>();
}
