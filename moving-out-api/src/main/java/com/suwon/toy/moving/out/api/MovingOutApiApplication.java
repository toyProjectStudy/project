package com.suwon.toy.moving.out.api;

import com.suwon.toy.moving.out.domain.configuration.MovingOutDomainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MovingOutDomainConfig.class)
@SpringBootApplication
public class MovingOutApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovingOutApiApplication.class, args);
    }

}
