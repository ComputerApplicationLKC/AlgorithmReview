package com.leekimcho.memberservice.global.exception;

import com.leekimcho.memberservice.domain.jwt.exception.BusinessException;
import com.leekimcho.memberservice.domain.jwt.exception.ExceptionCode;

public class JwtException extends BusinessException {
    public JwtException() {
        super(ExceptionCode.JWT_EXCEPTION);
    }
}