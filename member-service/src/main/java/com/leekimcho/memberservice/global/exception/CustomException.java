package com.leekimcho.memberservice.global.exception;

import com.leekimcho.memberservice.global.dto.Result;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 김승진 작성
 */

@Getter
public class CustomException extends RuntimeException {

    private HttpStatus status;
    private Result errorResult;

    protected CustomException(HttpStatus status, String message) {
        this.status = status;
        this.errorResult = Result.createErrorResult(message);
    }
}
