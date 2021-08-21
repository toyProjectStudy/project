/**
 * ===============================================================
 * File name : MovingOutDomainConfig.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.configuration;

import com.suwon.toy.moving.out.common.MovingOutCommon;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = {MovingOutCommon.class})
@EntityScan(basePackageClasses = {MovingOutCommon.class})
@SpringBootConfiguration
@ConfigurationPropertiesScan( basePackageClasses = {MovingOutCommon.class})
@EnableAutoConfiguration
@EnableJpaRepositories(
        basePackageClasses = {MovingOutCommon.class}
)
public class MovingOutCommonConfig {
}
