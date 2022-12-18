package com.leekimcho.guestservice.common.advice.exception;


import com.leekimcho.guestservice.common.advice.ExceptionCode;

/**
 * 김승진 작성
 */

public class JsonWriteException extends BusinessException {
    public JsonWriteException() {
        super(ExceptionCode.JSON_WRITE_ERROR);
    }
}