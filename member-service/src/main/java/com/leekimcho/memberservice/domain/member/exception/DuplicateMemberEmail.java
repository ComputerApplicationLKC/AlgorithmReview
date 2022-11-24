package com.leekimcho.memberservice.domain.member.exception;

import com.leekimcho.memberservice.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DuplicateMemberEmail extends CustomException {

    public DuplicateMemberEmail(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}