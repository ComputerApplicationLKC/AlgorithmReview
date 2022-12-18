package com.leekimcho.problemservice.common.advice.exception;


import com.leekimcho.problemservice.common.advice.ExceptionCode;

/**
 * 김승진 작성
 */

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(ExceptionCode.ENTITY_NOT_FOUND);
    }
}