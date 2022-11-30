package com.leekimcho.memberservice.global.utils.auth;

import com.leekimcho.memberservice.domain.member.dto.JwtPayload;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.domain.member.repository.MemberRepository;
import com.leekimcho.memberservice.domain.member.service.JwtService;
import com.leekimcho.memberservice.global.exception.UserAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Aspect
@RequiredArgsConstructor
@Component
public class AuthCheckAspect {

    private static final String AUTHORIZATION = "Authorization";

    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final HttpServletRequest httpServletRequest;

    @Around("@annotation(com.leekimcho.memberservice.global.utils.auth.AuthCheck)")
    public Object loginCheck(ProceedingJoinPoint pjp) throws Throwable {

        String token = httpServletRequest.getHeader(AUTHORIZATION);

        JwtPayload payload = jwtService.getPayload(token);
        log.info("AuthCheck(email) : " + payload.getEmail());

        Optional<Member> optionalMember = memberRepository.findByEmail(payload.getEmail());
        if(optionalMember.isEmpty()) {
            throw new UserAuthenticationException();
        }

        MemberContext.currentMember.set(optionalMember.get());
        return pjp.proceed();
    }
}