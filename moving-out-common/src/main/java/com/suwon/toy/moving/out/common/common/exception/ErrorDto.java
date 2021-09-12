/**
 * ===============================================================
 * File name : ErrorDto.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter // Getter가 반드시 필요함 https://yuja-kong.tistory.com/entry/Spring-Boot-ExceptionHandler-%EC%A0%81%EC%9A%A9-%EC%8B%9C-HttpMediaTypeNotAcceptableException-%EC%97%90%EB%9F%AC-%ED%95%B4%EA%B2%B0
public class ErrorDto {

    int errorCode;
    String errorMessage;

    public static ErrorDto buildErrorDto(CommonException commonException){
        return new ErrorDto(commonException.getErrorCode(), commonException.getErrorMessage());
    }
}
