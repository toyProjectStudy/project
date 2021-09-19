/**
 * ===============================================================
 * File name : UserControllerTest.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suwon.toy.moving.out.common.configuration.EnableMockMvc;
import com.suwon.toy.moving.out.common.movinguser.MovingUser;
import com.suwon.toy.moving.out.common.movinguser.MovingUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MovingUserRepository userRepository;

    @Order(1)
    @Test
    @DisplayName("정상적인 유저 가입이 허용되어야 합니다.")
    public void signup_api_success() throws Exception{
        ResultActions result = mockMvc.perform(post("/api/signup")
                        .content("{\n" +
                                "\t\"userId\":\"test1\",\n" +
                                "\t\"password\":\"1234\",\n" +
                                "\t\"username\":\"jay\",\n" +
                                "\t\"address\":\"사랑시 고백구 행복동\",\n" +
                                "\t\"phoneNumber\":\"010000000\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());

        String str = result.andReturn().getResponse().getContentAsString();
        System.out.println("Str ::: " + str);
        MovingUser returnedUser = objectMapper.readValue(str, MovingUser.class);
        assertEquals("jay", returnedUser.getName());

    }

    @Order(2)
    @Test
    @DisplayName("인증된 유저에 대한 유저 정보가 조회 되어야 합니다.")
    @WithMockUser(username = "test1", roles="USER")
    public void getMyUserInfo_success() throws Exception {
        mockMvc.perform(get("/api/user")).andDo(print())
                .andExpect(status().isOk());
//                .andExpect();
    }

    @Order(3)
    @Test
    @DisplayName("인증된 어드민 유저에 대한 유저 정보가 조회 되어야 합니다.")
    @WithMockUser(username = "test1", roles={"USER","ADMIN"})
    public void getMyUserInfo_ADmin_success() throws Exception {
        mockMvc.perform(get("/api/user/test1")).andDo(print())
                .andExpect(status().isOk());
    }

    @Order(4)
    @Test
    @DisplayName("중복된 회원 등록 시 UserAuthException을 Throw 되고 이를, 적절한 Error 메시지로 Client에 전달한다.")
    public void signup_api_fail_duplicated() throws Exception{
        userRepository.deleteAll(); // 테스트 전 DB 데이터 정보를 지워준다.
        userRepository.flush();

        mockMvc.perform(post("/api/signup")
                .content("{\n" +
                        "\t\"userId\":\"test1\",\n" +
                        "\t\"password\":\"1234\",\n" +
                        "\t\"username\":\"jay\",\n" +
                        "\t\"address\":null,\n" +
                        "\t\"phoneNumber\":\"010000000\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
        .andExpect(status().isOk()); // 첫 가입은 OK

        mockMvc.perform(post("/api/signup")
                    .content("{\n" +
                            "\t\"userId\":\"test1\",\n" +
                            "\t\"password\":\"1234\",\n" +
                            "\t\"username\":\"jay\",\n" +
                            "\t\"address\":null,\n" +
                            "\t\"phoneNumber\":\"010000000\"\n" +
                            "}")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isBadRequest()) // 400 BadRequest
            .andExpect(jsonPath("$.errorCode").exists())
            .andExpect(jsonPath("$.errorMessage").exists());
    }
}
