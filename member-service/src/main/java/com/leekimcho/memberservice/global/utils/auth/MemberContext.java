package com.leekimcho.memberservice.global.utils.auth;


import com.leekimcho.memberservice.domain.member.entity.Member;
import org.springframework.util.Assert;

public class MemberContext {
    public static ThreadLocal<Member> currentMember = new ThreadLocal<>();

    private final Member getContext() {
        Member member = currentMember.get();

        if (member == null) {
            member = createEmptyContext();
            currentMember.set(member);
        }

        return currentMember.get();
    }

    public static final void setContext(Member member) {
        Assert.notNull(member, "Only non-null UserContext instances are permitted");
        currentMember.set(member);
    }

    public static final Member createEmptyContext(){
        return Member.builder().build();
    }

}
