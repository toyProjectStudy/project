package com.suwon.toy.moving.out.api.auth.exception;

import com.suwon.toy.moving.out.common.common.exception.CommonErrorCode;
import com.suwon.toy.moving.out.common.common.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserAuthErrorCode implements ErrorCode {

    ALREADY_REGISTERD_USER(HttpStatus.BAD_REQUEST, "이미 등록된 사용자 입니다.");

    private HttpStatus errorStatus;
    private String errorMessage;

    UserAuthErrorCode(HttpStatus errorStatus, String errorMessage) {
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }
}
