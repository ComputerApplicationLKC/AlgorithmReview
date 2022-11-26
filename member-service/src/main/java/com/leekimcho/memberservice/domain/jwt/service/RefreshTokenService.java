package com.leekimcho.memberservice.domain.jwt.service;

import com.leekimcho.memberservice.domain.member.dto.JwtPayload;

public interface RefreshTokenService {

    void updateRefreshToken(Long id, String uuid);
    JwtPayload refreshJwtToken(String accessToken, String refreshToken);
    void logoutToken(String accessToken);

}
