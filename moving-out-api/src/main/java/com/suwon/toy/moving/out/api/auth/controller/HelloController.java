/**
 * ===============================================================
 * File name : HelloController.java
 * Created by injeahwang on 2021-07-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {


    @ApiOperation(value = "메인 용 테스트 API(명세가 확정되지 않음)")
    @GetMapping("/main")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("main이 들어올 예정입니다.");
    }
}
