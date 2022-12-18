package com.leekimcho.memberservice.global.exception;


/**
 * 김승진 작성
 */

public class UserAuthenticationException extends BusinessException {
    public UserAuthenticationException() {
        super(ExceptionCode.NOT_VALID_USER);
    }
}
