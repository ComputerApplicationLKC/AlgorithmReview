package com.leekimcho.reviewservice.common.advice.exception;


import com.leekimcho.reviewservice.common.advice.ExceptionCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(ExceptionCode.ENTITY_NOT_FOUND);
    }
}