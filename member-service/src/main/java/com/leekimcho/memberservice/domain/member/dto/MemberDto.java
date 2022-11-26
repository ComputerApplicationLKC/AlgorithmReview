package com.leekimcho.memberservice.domain.member.dto;

import com.leekimcho.memberservice.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String name;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
    }

    public MemberDto(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static MemberDto of(Member member) {
        return new MemberDto(member);
    }

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .build();
    }

}
