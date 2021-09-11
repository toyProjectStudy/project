/**
 * ===============================================================
 * File name : MovingoutExceptionHandler.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.exception.handler;

import com.suwon.toy.moving.out.api.auth.exception.UserAuthException;
import com.suwon.toy.moving.out.common.common.exception.CommonErrorCode;
import com.suwon.toy.moving.out.common.common.exception.CommonException;
import com.suwon.toy.moving.out.common.common.exception.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class MovingoutExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CommonException.class, UserAuthException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleCommonException(CommonException ex) {
        return new ResponseEntity(ErrorDto.buildErrorDto(ex),ex.getErrorStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleCommonException(Exception ex){
        CommonException commonException = new CommonException(CommonErrorCode.UNKNOWN_ERROR,ex.getCause());
        return new ResponseEntity(ErrorDto.buildErrorDto(commonException), commonException.getErrorStatus());
    }

    @ExceptionHandler(ServletException.class)
    public void handleServletException(ServletException ex) throws ServletException{
        throw ex;
    }

    /**
     *  Method Security 적용이 되면, SecurityConfig에서 설정한 AccessDeniedHandler를 타지 않고,
     *  ControllerAdvice로 등록한 ExceptionHandler를 타게 된다.
     *
     *  따라서, @PreAuthorized로 API 접근제어를 하게 되는 경우 ExceptionHandler에 AccessDeniedException Handler가 필요하다.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException ex){
        return new ResponseEntity(new ErrorDto(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()), HttpStatus.FORBIDDEN);
    }
}
