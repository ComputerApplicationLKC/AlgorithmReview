package com.leekimcho.problemservice.common.advice.exception;


import com.leekimcho.problemservice.common.advice.ExceptionCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(ExceptionCode.ENTITY_NOT_FOUND);
    }
}