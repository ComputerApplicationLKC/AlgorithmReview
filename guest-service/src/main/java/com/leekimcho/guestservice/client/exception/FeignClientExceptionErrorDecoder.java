package com.leekimcho.guestservice.client.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leekimcho.guestservice.common.Result;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 김승진 작성
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class FeignClientExceptionErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        String message = null;
        if (response.body() != null) {
            try {
                Result result = objectMapper.readValue(response.body().asInputStream(), Result.class);
                message = result.getMessage();
            } catch (IOException e) {
                String catchErrorMessage = "Error Deserializing response body from failed feign request response.";
                log.warn(methodKey + catchErrorMessage, e);

                return new FeignClientException(HttpStatus.INTERNAL_SERVER_ERROR, "고객센터로 문의해주세요.");
            }
        }

        return new FeignClientException(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}