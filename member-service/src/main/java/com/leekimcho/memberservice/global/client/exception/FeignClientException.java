package com.leekimcho.memberservice.global.client.exception;

import com.leekimcho.memberservice.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class FeignClientException extends CustomException {

    public FeignClientException(HttpStatus status, String message) {
        super(status, message);
    }
}
