package com.leekimcho.guestservice.common.advice.exception;


import com.leekimcho.guestservice.common.advice.ExceptionCode;

public class JsonWriteException extends BusinessException {
    public JsonWriteException() {
        super(ExceptionCode.JSON_WRITE_ERROR);
    }
}