package com.leekimcho.memberservice.domain.jwt.service;

public interface AccessTokenService {

    void checkAccessToken(String authorizationHeader);

}
