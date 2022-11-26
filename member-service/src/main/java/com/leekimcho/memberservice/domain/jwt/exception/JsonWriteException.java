package com.leekimcho.memberservice.domain.jwt.exception;

public class JsonWriteException extends BusinessException {
    public JsonWriteException() {
        super(ExceptionCode.JSON_WRITE_ERROR);
    }
}