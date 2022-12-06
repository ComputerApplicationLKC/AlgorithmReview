package com.leekimcho.memberservice.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leekimcho.memberservice.domain.member.dto.GoogleTokenDto;
import com.leekimcho.memberservice.domain.member.dto.JwtPayload;
import com.leekimcho.memberservice.domain.member.dto.LoginSuccessDto;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.global.config.properties.GoogleProperties;
import com.leekimcho.memberservice.global.config.security.PrincipalDetails;
import com.leekimcho.memberservice.global.config.security.SecurityConfig;
import com.leekimcho.memberservice.global.exception.JsonWriteException;
import com.leekimcho.memberservice.global.exception.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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



            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

            return LoginSuccessDto.builder()
                    .nickname(principal.getNickname())
                    .email(principal.getUsername())
                    .build();
        }
    }

}