package com.leekimcho.guestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * 김승진 작성
 */

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> {

    private int status;
    private String message;
    private T data;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResponseDto<?> of(HttpStatus httpStatus, String message) {
        int status = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
        return new ResponseDto<>(status, message);
    }

    public static <T> ResponseDto<T> of(HttpStatus httpStatus, String message, T data) {
        int status = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
        return new ResponseDto<>(status, message, data);
    }

    public static ResponseDto<?> fail(HttpStatus httpStatus, String message) {
        return new ResponseDto<>(httpStatus.value(), message, null);
    }
}
