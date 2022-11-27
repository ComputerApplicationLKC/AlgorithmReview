package com.leekimcho.memberservice.global.exception;


public class UserAuthenticationException extends BusinessException {
    public UserAuthenticationException() {
        super(ExceptionCode.NOT_VALID_USER);
    }
}
