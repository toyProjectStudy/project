package com.suwon.toy.moving.out.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class MovingOutAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovingOutAdminApplication.class, args);
    }

}
