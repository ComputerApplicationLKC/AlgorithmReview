package com.leekimcho.problemservice.client;


import com.leekimcho.problemservice.common.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="member-service")
public interface ServiceClient {
    @GetMapping(value = "/api/member-service/member-context", consumes="application/json")
    MemberDto getMemberContext();
}
