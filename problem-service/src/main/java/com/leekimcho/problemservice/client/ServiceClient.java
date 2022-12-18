package com.leekimcho.problemservice.client;


import com.leekimcho.problemservice.common.dto.ContextRequest;
import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.common.dto.MemberIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 김승진 작성
 */

@FeignClient(name="member-service")
public interface ServiceClient {
    @PostMapping(value = "/api/member-service/member-context", consumes="application/json")
    MemberDto getMemberContext(@RequestBody String email);

    @PostMapping(value = "/api/member-service/member-context/problem", consumes="application/json")
    MemberIdDto getMemberProblem(@RequestBody ContextRequest ctx);

}
