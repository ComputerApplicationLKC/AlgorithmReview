package com.leekimcho.problemservice.client;


import com.leekimcho.problemservice.common.dto.MemberDto;
import com.leekimcho.problemservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name="member-service", configuration = FeignClientConfig.class)
public interface ServiceClient {
    @GetMapping(value = "/api/member-service/member-context")
    MemberDto getMemberContext();
}
