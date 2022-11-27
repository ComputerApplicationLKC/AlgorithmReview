package com.leekimcho.memberservice.global.exception;


import static com.leekimcho.memberservice.global.exception.ExceptionCode.*;

public class JwtException extends BusinessException {
    public JwtException() {
        super(JWT_EXCEPTION);
    }
}