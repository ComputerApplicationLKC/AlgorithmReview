package com.leekimcho.guestservice.common.advice.exception;

import com.leekimcho.guestservice.common.Result;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private HttpStatus status;
    private Result errorResult;

    protected CustomException(HttpStatus status, String message) {
        this.status = status;
        this.errorResult = Result.createErrorResult(message);
    }
}
