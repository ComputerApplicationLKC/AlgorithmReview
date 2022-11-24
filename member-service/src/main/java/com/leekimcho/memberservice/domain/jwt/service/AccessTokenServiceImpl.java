package com.leekimcho.memberservice.domain.jwt.service;

import com.leekimcho.memberservice.domain.jwt.exception.AccessTokenNotValidException;
import com.leekimcho.memberservice.global.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService{

    private final JwtTokenProvider tokenProvider;

    @Override
    public void checkAccessToken(String authorizationHeader) {

        String token = authorizationHeader.replace("Bearer", "");

        if (!tokenProvider.validateJwtToken(token)) {
            throw new AccessTokenNotValidException("Access Token is not Valid");
        }
    }

}
