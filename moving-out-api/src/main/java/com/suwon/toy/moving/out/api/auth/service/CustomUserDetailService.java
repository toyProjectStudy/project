/**
 * ===============================================================
 * File name : CustomUserDetailService.java
 * Created by injeahwang on 2021-07-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth.service;

import com.suwon.toy.moving.out.common.movinguser.MovingUser;
import com.suwon.toy.moving.out.common.movinguser.UserAuthorityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    private final UserAuthorityRepository userRepository;

    public CustomUserDetailService(UserAuthorityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findOneWithAuthoritiesByUserId(userId)
                .map(user->createUser(userId, user))
                .orElseThrow(()->new UsernameNotFoundException(userId+"->데이터베이스에 존재하지 않는 유저입니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, MovingUser user){
//        if(!user.isActivated()) {
//            throw new RuntimeException(username + " -> 활성화 되어 있지 않습니다.");
//        }

        List<GrantedAuthority> grantedAuthorityList = user.getAuthoritySet().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserId(),
                user.getPassword(),
                grantedAuthorityList);
    }
}
