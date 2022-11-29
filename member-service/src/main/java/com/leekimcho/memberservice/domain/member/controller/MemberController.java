package com.leekimcho.memberservice.domain.member.controller;

import com.leekimcho.memberservice.domain.member.dto.GoogleTokenDto;
import com.leekimcho.memberservice.domain.member.dto.MemberDto;
import com.leekimcho.memberservice.domain.member.service.MemberService;
import com.leekimcho.memberservice.domain.member.service.OauthService;
import com.leekimcho.memberservice.global.dto.ResponseDto;
import com.leekimcho.memberservice.global.dto.Result;
import com.leekimcho.memberservice.global.utils.auth.AuthCheck;
import com.leekimcho.memberservice.global.utils.auth.MemberContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.leekimcho.memberservice.global.advice.message.SuccessMessage.SUCCESS_AUTHORIZATION;
import static com.leekimcho.memberservice.global.advice.message.SuccessMessage.SUCCESS_ISSUE_TOKEN;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member-service")
public class MemberController {

    private final MemberService memberService;
    private final OauthService oauthService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> joinAdmin(@RequestBody MemberDto dto) {
        memberService.registerAdmin(dto, true);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

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
    public ResponseEntity<Result<GetMemberResponse>> getMemberContext() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.createSuccessResult(new GetMemberResponse(new MemberDto(MemberContext.currentMember.get()))));
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    static class GetMemberResponse {
        private Long memberId;
        private String nickname;

        public GetMemberResponse(MemberDto memberDto) {
            this.memberId = memberDto.getId();
            this.nickname = memberDto.getNickname();
        }
    }

}