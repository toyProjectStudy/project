/**
 * ===============================================================
 * File name : TokenExceptionTest.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suwon.toy.moving.out.api.auth.dto.TokenDto;
import com.suwon.toy.moving.out.api.auth.dto.UserDto;
import com.suwon.toy.moving.out.api.auth.service.MovingUserAuthService;
import com.suwon.toy.moving.out.api.auth.util.jwt.TokenProvider;
import com.suwon.toy.moving.out.common.configuration.EnableMockMvc;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@EnableMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TokenExceptionTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MovingUserAuthService movingUserAuthService;

    @MockBean
    TokenProvider tokenProvider;

    @BeforeAll
    public void setUp(){
        UserDto userDto = new UserDto();

        userDto.setUserId("test1");
        userDto.setPassword("1234");
        userDto.setUsername("tester");
        userDto.setPhoneNumber("00000000");
        movingUserAuthService.signup(userDto);
    }


    @Test
    @DisplayName("발급받은 토큰이 유효시간 초과 시 발생하는 에러입니다. ExpiredJwtException")
    public void token_time_expired_test() throws Exception {
        given(tokenProvider.createToken(any())).willReturn("testToken");

        // 먼저 signin 메서드를 호출해서
        ResultActions result = mockMvc.perform(post("/api/authenticate")
                .content("{\n" +
                        "\t\"userId\":\"test1\",\n" +
                        "\t\"password\":\"1234\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.token").exists());

        String str = result.andReturn().getResponse().getContentAsString();

        System.out.println("Result  Token : " + str);
        TokenDto tokenDto = objectMapper.readValue(str, TokenDto.class);

        // JWT Token 검증시 무조건 만료된 토큰 결과가 나오도록 Mocking
        given(tokenProvider.validateToken(any())).willThrow(ExpiredJwtException.class);
        mockMvc.perform(get("/api/employee/list")
                        .header("authorization", "Bearer "+tokenDto.getToken()))
                .andDo(print())
                .andExpect(status().isForbidden()) // 토큰 만료시 HTTP 403 Forbidden 을 리턴한다.
                .andExpect(jsonPath(("$.errorMessage")).exists())
                .andExpect(jsonPath(("$.errorMessage")).value("JWT Token Was Expired!"));
    }

    @Test
    @DisplayName("jwt filter에서 토큰 자체 유효성 검증에 실패한 케이스 테스트. IllegalArgumentException")
    public void token_time_IllegalArgumentException_test() throws Exception {
//
        given(tokenProvider.createToken(any())).willReturn("testToken");

        // 먼저 signin 메서드를 호출해서
        ResultActions result = mockMvc.perform(post("/api/authenticate")
                        .content("{\n" +
                                "\t\"userId\":\"test1\",\n" +
                                "\t\"password\":\"1234\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.token").exists());

        String str = result.andReturn().getResponse().getContentAsString();

        System.out.println("Result  Token : " + str);
        TokenDto tokenDto = objectMapper.readValue(str, TokenDto.class);


        // JWT Token 검증시 무조건 만료된 토큰 결과가 나오도록 Mocking
        given(tokenProvider.validateToken(any())).willThrow(MalformedJwtException.class);

        mockMvc.perform(get("/api/employee/list")
                        .header("authorization", "Bearer "+tokenDto.getToken()))
                .andDo(print())
                .andExpect(status().isBadRequest()) // 서버에서 유효하지 않은 토큰으로 판단시 HTTP 400 BAD_REQUEST 을 리턴한다.
                .andExpect(jsonPath(("$.errorMessage")).exists())
                .andExpect(jsonPath(("$.errorMessage")).value("InValid JWT Token!"));
    }
}
