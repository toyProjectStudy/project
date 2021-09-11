/**
 * ===============================================================
 * File name : UserAuthException.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth.exception;

import com.suwon.toy.moving.out.common.common.exception.CommonException;
import com.suwon.toy.moving.out.common.common.exception.ErrorCode;

public class UserAuthException extends CommonException {

    public static UserAuthException build(
        UserAuthErrorCode userAuthErrorCode,
        Throwable cause
    ){
        return new UserAuthException(userAuthErrorCode, cause);
    }

    public static UserAuthException build(
            UserAuthErrorCode userAuthErrorCode
            ){
        return new UserAuthException(userAuthErrorCode);
    }

    public UserAuthException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserAuthException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
