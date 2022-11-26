package com.leekimcho.memberservice.domain.jwt.exception;

public class UserAuthenticationException extends BusinessException {
    public UserAuthenticationException() {
        super(ExceptionCode.NOT_VALID_USER);
    }
}