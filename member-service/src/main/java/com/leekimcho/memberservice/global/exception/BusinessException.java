package com.leekimcho.memberservice.global.exception;

import lombok.Getter;

/**
 * 김승진 작성
 */

@Getter
public class BusinessException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}