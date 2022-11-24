package com.leekimcho.memberservice.domain.member.controller;

import com.leekimcho.memberservice.domain.member.dto.MemberDto;
import com.leekimcho.memberservice.domain.member.service.MemberService;
import com.leekimcho.memberservice.global.dto.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity getMemberByToken(@Valid @RequestHeader(value="Member-id") String MemberId) {

        MemberDto memberDto = memberService.findMemberByMemberId(Long.parseLong(MemberId));

        GetMemberByTokenResponse getMemberByTokenResponse = new GetMemberByTokenResponse(memberDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.createSuccessResult(getMemberByTokenResponse));
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    static class GetMemberByTokenResponse {
        private Long MemberId;
        private String email;
        private String MemberName;
        private String phoneNumber;

        public GetMemberByTokenResponse(MemberDto memberDto) {
            this.MemberId = memberDto.getId();
            this.email = memberDto.getEmail();
            this.MemberName = memberDto.getName();
        }
    }


    @Data @NoArgsConstructor @AllArgsConstructor
    static class GetmemberResponse {
        private Long MemberId;
        private String MemberName;
        private String phoneNumber;

        public GetmemberResponse(MemberDto memberDto) {
            this.MemberId = memberDto.getId();
            this.MemberName = memberDto.getName();
        }
    }

    @Data
    static class StoreOwnerByTokenResponse {
        private Long id;
        private String name;

        public StoreOwnerByTokenResponse(MemberDto dto) {
            this.id = dto.getId();
            this.name = dto.getName();
        }
    }
}