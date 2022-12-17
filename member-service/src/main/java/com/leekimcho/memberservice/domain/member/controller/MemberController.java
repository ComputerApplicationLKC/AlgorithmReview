package com.leekimcho.memberservice.domain.member.controller;

import com.leekimcho.memberservice.domain.member.dto.GoogleTokenDto;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.domain.member.service.MemberService;
import com.leekimcho.memberservice.domain.member.service.OauthService;
import com.leekimcho.memberservice.global.dto.ResponseDto;
import com.leekimcho.memberservice.global.utils.auth.AuthCheck;
import com.leekimcho.memberservice.global.utils.auth.MemberContext;
import com.leekimcho.memberservice.messagequeue.KafkaProducer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static com.leekimcho.memberservice.global.advice.message.SuccessMessage.SUCCESS_AUTHORIZATION;
import static com.leekimcho.memberservice.global.advice.message.SuccessMessage.SUCCESS_ISSUE_TOKEN;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member-service")
public class MemberController {

    private final OauthService oauthService;
    private final MemberService memberService;
    private final KafkaProducer kafkaProducer;

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
    public ResponseEntity<?> logout() {
        MemberContext.currentMember.remove();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<Member>> registerMember(@RequestBody Member member) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, SUCCESS_AUTHORIZATION, memberService.saveMember(member)));
    }

    @PostMapping("/member-context")
    public MemberDto getMemberContext(@RequestBody String email) {
        MemberDto dto = new MemberDto(memberService.findMemberByEmail(email).orElseThrow(EntityNotFoundException::new));
        return dto;
    }

    @PostMapping("/member-context/problem")
    public MemberIdDto getMemberProblem(@RequestBody ContextRequest ctx) {
        MemberIdDto dto = new MemberIdDto(memberService.findMemberByEmail(ctx.getEmail()).get(), ctx.getId());
        kafkaProducer.send("problem-topic", dto);
        return dto;
    }

    @PostMapping("/member-context/guest")
    public MemberIdDto getMemberGuest(@RequestBody ContextRequest ctx) {
        MemberIdDto dto = new MemberIdDto(memberService.findMemberByEmail(ctx.getEmail()).get(), ctx.getId());
        kafkaProducer.send("guest-topic", dto);
        return dto;
    }

    @Data
    public static class ContextRequest {
        private String email;
        private Long id;
    }

    @Data
    @NoArgsConstructor
    public static class MemberDto {
        private Long memberId;
        private String username;

        public MemberDto(Member member) {
            this.memberId = member.getId();
            this.username = member.getUsername();
        }

    }

    public static class MemberIdDto extends MemberDto{
        private Long id;

        public MemberIdDto(Member member, Long id) {
            super(member);
            this.id = id;
        }

    }

}