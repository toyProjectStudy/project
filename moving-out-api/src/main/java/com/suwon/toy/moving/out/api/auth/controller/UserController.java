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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "신규 가입(계정 가입)")
    @PostMapping("/signup")
    public ResponseEntity<MovingUser> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @ApiOperation(value = "유저 정보 조회(로그인한 User)")
    @GetMapping("/user")
    @ApplyUserAdminRole
    public ResponseEntity<MovingUser> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @ApiOperation(value = "UserId로 사용자 조회(Admin 전용)")
    @GetMapping("/user/{userId}")
    @ApplyAdminRole
    public ResponseEntity<MovingUser> getUserInfo(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(userId).get());
    }
}
