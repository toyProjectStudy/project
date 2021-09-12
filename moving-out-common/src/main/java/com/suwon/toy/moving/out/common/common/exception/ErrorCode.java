/**
 * ===============================================================
 * File name : ErrorCode.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getErrorStatus();
    String getErrorMessage();
}
