package com.leekimcho.problemservice.client;


import com.leekimcho.problemservice.common.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name="member-service")
public interface ServiceClient {
    @GetMapping("/member-service/api/member-context")
    MemberDto getMemberContext();
}
