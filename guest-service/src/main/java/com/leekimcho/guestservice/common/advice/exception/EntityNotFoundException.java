package com.leekimcho.guestservice.common.advice.exception;


import com.leekimcho.guestservice.common.advice.ExceptionCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(ExceptionCode.ENTITY_NOT_FOUND);
    }
}