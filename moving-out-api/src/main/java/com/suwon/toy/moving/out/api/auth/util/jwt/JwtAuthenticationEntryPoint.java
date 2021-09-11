/**
 * ===============================================================
 * File name : JwtAuthenticationEntryPoint.java
 * Created by injeahwang on 2021-07-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth.util.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        String reqException = (String) request.getAttribute("exception");
        if(reqException.equals(JwtErrorCode.JWT_EXPIRED.getValue())){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("{\"errorMessage\":\"JWT Token Was Expired!\"}");
        } else if(reqException.equals(JwtErrorCode.JWT_INVALID.getValue())){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //400
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("{\"errorMessage\":\"InValid JWT Token!\"}");
        }
        else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }


    }
}
