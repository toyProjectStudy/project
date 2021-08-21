/**
 * ===============================================================
 * File name : MovingUserRepositoryTest.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.movinguser;


import com.suwon.toy.moving.out.common.configuration.MovingOutCommonConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Import(MovingOutCommonConfig.class)
public class MovingUserRepositoryTest {

    @Autowired
    MovingUserRepository movingUserRepository;

    @Test
    @DisplayName("이사 희망 고객을 DB에 등록한다.")
    public void movingUserRegisterTest(){
        MovingUser newMovingUser = new MovingUser();
        newMovingUser.setUserId(UUID.randomUUID().toString()); // TODO : ID 발번 정책?
        newMovingUser.setAddress("사랑시 고백구 행복동");
        newMovingUser.setEmail("00000@gmail.com");
        newMovingUser.setName("신짱구");
        newMovingUser.setPhone("010-0000-0000");
        newMovingUser.setPassword("1234"); // TODO : Password 암호화 전략 확인 필요.

        assertDoesNotThrow(()->this.movingUserRepository.save(newMovingUser));
        this.movingUserRepository.flush();

        assertEquals(newMovingUser.getUserId(),
                this.movingUserRepository.findById(newMovingUser.getUserId()).get().getUserId());
    }

}
