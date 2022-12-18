package com.leekimcho.memberservice.domain.member.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 김승진 작성
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role authority;

}
