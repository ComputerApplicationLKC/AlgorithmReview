package com.leekimcho.memberservice.global.exception;


/**
 * 김승진 작성
 */

public class JsonWriteException extends BusinessException {
    public JsonWriteException() {
        super(ExceptionCode.JSON_WRITE_ERROR);
    }
}