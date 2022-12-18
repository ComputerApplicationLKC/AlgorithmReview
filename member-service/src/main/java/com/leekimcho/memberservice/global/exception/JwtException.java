package com.leekimcho.memberservice.global.exception;


import static com.leekimcho.memberservice.global.exception.ExceptionCode.*;

/**
 * 김승진 작성
 */

public class JwtException extends BusinessException {
    public JwtException() {
        super(JWT_EXCEPTION);
    }
}