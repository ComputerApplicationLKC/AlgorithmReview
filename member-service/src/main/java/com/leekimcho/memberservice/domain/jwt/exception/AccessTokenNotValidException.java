package com.leekimcho.memberservice.domain.jwt.exception;

import com.leekimcho.memberservice.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AccessTokenNotValidException extends CustomException {

    public AccessTokenNotValidException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}