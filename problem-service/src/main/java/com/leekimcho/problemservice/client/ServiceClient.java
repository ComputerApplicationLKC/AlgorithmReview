package com.leekimcho.problemservice.client;


import com.leekimcho.problemservice.common.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="member", path="/api/member-service")
public interface ServiceClient {
    @feign.Headers("Content-Type: application/json")
    @GetMapping(value = "/member-context", produces="application/json", consumes="application/json")
    ResponseEntity<MemberDto> getMemberContext();
}
