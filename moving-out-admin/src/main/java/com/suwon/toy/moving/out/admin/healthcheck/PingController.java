/**
 * ===============================================================
 * File name : PingController.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.admin.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PingController {

    @GetMapping("/api/ping")
    public Map<String,String> pingpong(){
        Map<String, String> msg = new HashMap<>();
        msg.put("service", "moving-out-admin");
        msg.put("ping", "pong");
        return msg;
    }
}
