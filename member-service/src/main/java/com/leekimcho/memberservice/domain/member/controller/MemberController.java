package com.leekimcho.memberservice.domain.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leekimcho.memberservice.domain.member.dto.GoogleTokenDto;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.domain.member.service.OauthService;
import com.leekimcho.memberservice.global.dto.ResponseDto;
import com.leekimcho.memberservice.global.utils.auth.AuthCheck;
import com.leekimcho.memberservice.global.utils.auth.MemberContext;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;

import static com.leekimcho.memberservice.global.advice.message.SuccessMessage.SUCCESS_AUTHORIZATION;
import static com.leekimcho.memberservice.global.advice.message.SuccessMessage.SUCCESS_ISSUE_TOKEN;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member-service")
public class MemberController {

    private final OauthService oauthService;

    @AuthCheck
    @GetMapping("/check")
    public ResponseEntity<?> checkAuth() {
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_AUTHORIZATION));
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody @Valid GoogleTokenDto tokenDto) {
        return ResponseEntity.ok().body(ResponseDto.of(
                HttpStatus.OK, SUCCESS_ISSUE_TOKEN, oauthService.googleLogin(tokenDto.getAccessToken()))
        );
    }

    @GetMapping("/member-context")
    public ResponseEntity<String> getMemberContext() {
        return ResponseEntity.ok().body((new MemberDto(MemberContext.currentMember.get()).toString()));
    }

    @Data
    public static class MemberDto {
        private Long memberId;
        private String nickname;

        public MemberDto(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
        }

        public String toString() {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(this);
        }
    }

}