/**
 * ===============================================================
 * File name : MovingUserServiceTest.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth;

import com.suwon.toy.moving.out.api.auth.dto.UserDto;
import com.suwon.toy.moving.out.api.auth.service.MovingUserAuthService;
import com.suwon.toy.moving.out.common.movinguser.Authority;
import com.suwon.toy.moving.out.common.movinguser.MovingUser;
import com.suwon.toy.moving.out.common.movinguser.UserAuthorityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
public class MovingUserServiceTest {


    UserAuthorityRepository userAuthorityRepository = Mockito.mock(UserAuthorityRepository.class);

    MovingUserAuthService movingUserAuthService;

    private Set<Authority> authoritySet;
    private final UserDto userDto;
    private final MovingUser savedNewUser;
    public MovingUserServiceTest(){
        this.movingUserAuthService = new MovingUserAuthService(this.userAuthorityRepository, new BCryptPasswordEncoder());

        this.authoritySet = new HashSet<>();
        this.authoritySet.add(new Authority("ROLE_USER"));
        this.userDto = new UserDto("test1", "1234", "jay", null, "00000000",null,null);
        this.savedNewUser = new MovingUser(
                "test1",
                "jay",
                null,
                null,
                "00000000",
                "$2a$10$toMOBYhmDrZdi7JIzYnA6.YtbQSyxTpLo.Y4RHT50OlI6Ucn8mCQS",
                null,
                authoritySet
        );
    }

    @Test
    @DisplayName("회원 가입 기능이 정상적으로 동작해야 합니다.")
    public void signup_success(){
        given(userAuthorityRepository.save(any())).willReturn(savedNewUser);
        MovingUser savedUser = this.movingUserAuthService.signup(userDto);

        assertEquals(savedNewUser.getUserId(),savedUser.getUserId());
    }

    @Test
    @DisplayName("회원의 권한과 정보 조회가 정상적으로 동작해야 합니다.")
    public void getUserWithAuthorities_success(){
        given(userAuthorityRepository.findOneWithAuthoritiesByUserId(any())).willReturn(Optional.of(savedNewUser));

        Optional<MovingUser> searchedUser = this.movingUserAuthService.getUserWithAuthorities(userDto.getUserId());

        assertTrue(searchedUser.isPresent());
        assertEquals(savedNewUser.getUserId(), searchedUser.get().getUserId());
    }


}
