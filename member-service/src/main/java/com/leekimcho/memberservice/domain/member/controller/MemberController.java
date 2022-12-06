package com.leekimcho.memberservice.domain.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leekimcho.memberservice.domain.member.dto.GoogleTokenDto;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.domain.member.service.MemberService;
import com.leekimcho.memberservice.domain.member.service.OauthService;
import com.leekimcho.memberservice.global.advice.message.SuccessMessage;
import com.leekimcho.memberservice.global.config.security.PrincipalDetails;
import com.leekimcho.memberservice.global.dto.ResponseDto;
import com.leekimcho.memberservice.global.utils.auth.AuthCheck;
import com.leekimcho.memberservice.global.utils.auth.MemberContext;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.Principal;

import static com.leekimcho.memberservice.global.advice.message.SuccessMessage.SUCCESS_AUTHORIZATION;
import static com.leekimcho.memberservice.global.advice.message.SuccessMessage.SUCCESS_ISSUE_TOKEN;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member-service")
public class MemberController {

    private final OauthService oauthService;
    private final MemberService memberService;

    @AuthCheck
    @GetMapping("/check")
    public ResponseEntity<?> checkAuth() {
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_AUTHORIZATION));
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleTokenDto tokenDto) {
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_ISSUE_TOKEN, oauthService.googleLogin(tokenDto))
        );
    }

    @GetMapping("/logout")
    public ResponseEntity<MemberDto> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<Member>> registerMember(@RequestBody Member member) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, SUCCESS_AUTHORIZATION, memberService.saveMember(member)));
    }

    @GetMapping("/member-context")
    public ResponseEntity<MemberDto> getMemberContext(Principal principal) {
        return ResponseEntity.ok().body(new MemberDto((PrincipalDetails) principal));
    }

    @Data
    public static class MemberDto {
        private Long memberId;
        private String nickname;

        public MemberDto(PrincipalDetails pd) {
            this.memberId = pd.getUserId();
            this.nickname = pd.getNickname();
        }

    }

}