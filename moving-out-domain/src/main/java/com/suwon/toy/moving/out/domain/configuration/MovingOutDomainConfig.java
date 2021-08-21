/**
 * ===============================================================
 * File name : MovingOutDomainConfig.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.domain.configuration;

import com.suwon.toy.moving.out.domain.MovingOutDomain;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = {MovingOutDomain.class})
@EntityScan(basePackageClasses = {MovingOutDomain.class})
@SpringBootConfiguration
@ConfigurationPropertiesScan(basePackages = {"com.suwon.toy"})
@EnableJpaRepositories(
        basePackageClasses = {MovingOutDomain.class}
)
@EnableAutoConfiguration
public class MovingOutDomainConfig {
}
