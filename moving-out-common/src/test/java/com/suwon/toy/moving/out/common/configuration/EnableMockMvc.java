package com.suwon.toy.moving.out.common.configuration;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.lang.annotation.*;

// MockMvc에서 한글 깨짐 현상이 발생
// https://pompitzz.github.io/blog/Spring/MockMvc_Encoding.html#%E1%84%92%E1%85%A2%E1%84%80%E1%85%A7%E1%86%AF%E1%84%87%E1%85%A5%E1%86%B8
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AutoConfigureMockMvc
@Import(EnableMockMvc.MockMvcTestConfig.class)
public @interface EnableMockMvc {
    class MockMvcTestConfig {
        @Bean
        @Primary
        public CharacterEncodingFilter characterEncodingFilter() {
            return new CharacterEncodingFilter("UTF-8", true);
        }
    }
}
