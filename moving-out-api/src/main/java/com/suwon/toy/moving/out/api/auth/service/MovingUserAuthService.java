/**
 * ===============================================================
 * File name : UserService.java
 * Created by injeahwang on 2021-07-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth.service;


import com.suwon.toy.moving.out.api.auth.dto.UserDto;
import com.suwon.toy.moving.out.api.auth.exception.UserAuthErrorCode;
import com.suwon.toy.moving.out.api.auth.exception.UserAuthException;
import com.suwon.toy.moving.out.api.auth.util.SecurityUtil;
import com.suwon.toy.moving.out.common.movinguser.Authority;
import com.suwon.toy.moving.out.common.movinguser.MovingUser;
import com.suwon.toy.moving.out.common.movinguser.UserAuthorityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class MovingUserAuthService {
    private final UserAuthorityRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MovingUserAuthService(UserAuthorityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MovingUser signup(UserDto userDto) {
        if(userRepository.findOneWithAuthoritiesByUserId(userDto.getUserId()).orElse(null) != null) {
            throw UserAuthException.build(UserAuthErrorCode.ALREADY_REGISTERD_USER); // 이미 가입된 유저입니다.
        }

        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");


        MovingUser user = new MovingUser();
        user.setUserId(userDto.getUserId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getUsername());
        user.setPhone(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setAuthoritySet(Collections.singleton(authority));

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<MovingUser> getUserWithAuthorities(String userId) {
        return userRepository.findOneWithAuthoritiesByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Optional<MovingUser> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUserId);
    }
}
