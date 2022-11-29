package com.leekimcho.memberservice.domain.member.entity;

import com.leekimcho.memberservice.domain.member.dto.MemberDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(unique = true)
    private String socialId;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private AuthType oauthType;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;


    @Builder
    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.nickname = nickname;
    }

    public Member(MemberDto dto, boolean flag) {
        this.email = dto.getEmail();
        this.password = new BCryptPasswordEncoder().encode(dto.getPassword());
        this.nickname = dto.getNickname();
        if (flag) {
            this.oauthType = AuthType.GOOGLE;
            this.roleType = RoleType.ROLE_ADMIN;
        }
    }

}
