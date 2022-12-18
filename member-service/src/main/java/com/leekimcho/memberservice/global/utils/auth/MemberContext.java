package com.leekimcho.memberservice.global.utils.auth;


import com.leekimcho.memberservice.domain.member.entity.Member;

/**
 * 김승진 작성
 */

public class MemberContext {
    public static ThreadLocal<Member> currentMember = new InheritableThreadLocal<>() {
        @Override
        protected Member initialValue() {
            return Member.builder().build();
        }

    };

}
