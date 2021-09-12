/**
 * ===============================================================
 * File name : UserController.java
 * Created by injeahwang on 2021-07-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth.controller;


import com.suwon.toy.moving.out.api.auth.dto.UserDto;
import com.suwon.toy.moving.out.api.auth.service.MovingUserAuthService;
import com.suwon.toy.moving.out.common.common.annotation.security.ApplyAdminRole;
import com.suwon.toy.moving.out.common.common.annotation.security.ApplyUserAdminRole;
import com.suwon.toy.moving.out.common.movinguser.MovingUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private final MovingUserAuthService userService;

    public UserController(MovingUserAuthService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<MovingUser> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @ApplyUserAdminRole
    public ResponseEntity<MovingUser> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{userId}")
    @ApplyAdminRole
    public ResponseEntity<MovingUser> getUserInfo(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(userId).get());
    }
}
