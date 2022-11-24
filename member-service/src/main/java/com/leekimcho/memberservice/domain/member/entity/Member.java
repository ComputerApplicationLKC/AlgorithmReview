package com.leekimcho.memberservice.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private AuthType oauthType = AuthType.GOOGLE;


    @Column(insertable = false,updatable = false)
    protected String dtype;

    @Builder
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.name = name;
    }

}
