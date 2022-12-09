package com.leekimcho.memberservice.domain.member.service;

import com.leekimcho.memberservice.domain.member.dto.GoogleTokenDto;
import com.leekimcho.memberservice.domain.member.dto.LoginSuccessDto;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.global.utils.auth.MemberContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OauthService {
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;


    public LoginSuccessDto googleLogin(GoogleTokenDto dto) {
        Optional<Member> optional = memberService.findMemberByEmail(dto.getEmail());

        if (optional.isEmpty()) { // 로그인 불가
            log.info("[GUEST 로그인] {}", dto.getEmail());
            throw new EntityNotFoundException();
        } else {                 // 로그인
            log.info("[USER 로그인] {}", dto.getEmail());
            Member member = optional.get();


            MemberContext.currentMember.set(member);

            return LoginSuccessDto.builder()
                    .nickname(member.getUsername())
                    .email(member.getEmail())
                    .build();
        }
    }

}