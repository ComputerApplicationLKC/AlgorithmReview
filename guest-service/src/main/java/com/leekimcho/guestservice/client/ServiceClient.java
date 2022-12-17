package com.leekimcho.guestservice.client;


import com.leekimcho.guestservice.dto.ContextRequest;
import com.leekimcho.guestservice.dto.MemberDto;
import com.leekimcho.guestservice.dto.MemberIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="member-service")
public interface ServiceClient {
    @PostMapping(value = "/api/member-service/member-context", consumes="application/json")
    MemberDto getMemberContext(@RequestBody String email);

    @PostMapping(value = "/api/member-service/member-context/guest", consumes="application/json")
    MemberIdDto getMemberGuest(@RequestBody ContextRequest ctx);

}
