package com.leekimcho.problemservice.common.advice.exception;


import com.leekimcho.problemservice.common.advice.ExceptionCode;

public class JsonWriteException extends BusinessException {
    public JsonWriteException() {
        super(ExceptionCode.JSON_WRITE_ERROR);
    }
}