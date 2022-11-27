package com.leekimcho.memberservice.global.exception;


public class JsonWriteException extends BusinessException {
    public JsonWriteException() {
        super(ExceptionCode.JSON_WRITE_ERROR);
    }
}