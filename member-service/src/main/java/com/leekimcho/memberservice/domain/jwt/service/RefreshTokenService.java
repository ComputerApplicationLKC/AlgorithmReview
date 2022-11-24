package com.leekimcho.memberservice.domain.jwt.service;

import com.leekimcho.memberservice.domain.member.dto.JwtTokenDto;

public interface RefreshTokenService {

    void updateRefreshToken(Long id, String uuid);
    JwtTokenDto refreshJwtToken(String accessToken, String refreshToken);
    void logoutToken(String accessToken);

}
