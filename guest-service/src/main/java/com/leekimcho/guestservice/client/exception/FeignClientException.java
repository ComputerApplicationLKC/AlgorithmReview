package com.leekimcho.guestservice.client.exception;

import com.leekimcho.guestservice.common.advice.exception.CustomException;
import org.springframework.http.HttpStatus;

/**
 * 김승진 작성
 */

public class FeignClientException extends CustomException {

    public FeignClientException(HttpStatus status, String message) {
        super(status, message);
    }
}
