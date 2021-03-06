/**
 * ===============================================================
 * File name : CommonErrorCode.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonErrorCode implements ErrorCode{

    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 에러 입니다.");

    private HttpStatus errorStatus;
    private String errorMessage;

    CommonErrorCode(HttpStatus errorStatus, String errorMessage) {
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }
}
