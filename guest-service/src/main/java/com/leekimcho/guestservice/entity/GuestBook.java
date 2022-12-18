package com.leekimcho.guestservice.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 김승진 작성
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class GuestBook extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "guest_id")
    private Long id;

    @Column
    private String nickname;

    @Column(nullable = true)
    private Long memberId;

    @Column
    private String content;
}