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
    private String nickname;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
    }

    public MemberDto(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static MemberDto of(Member member) {
        return new MemberDto(member);
    }

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .build();
    }

}
