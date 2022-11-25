package com.leekimcho.guestservice.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class GuestBook extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "guest_id")
    private Long id;

    @Column
    private String nickname;

    @Column
    private String content;
}