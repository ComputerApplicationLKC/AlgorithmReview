package com.leekimcho.problemservice.common.advice.exception;

import com.leekimcho.problemservice.common.advice.ExceptionCode;
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
