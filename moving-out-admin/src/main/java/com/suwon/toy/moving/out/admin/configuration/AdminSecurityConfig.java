/**
 * ===============================================================
 * File name : AdminSecurityConfig.java
 * Created by injeahwang on 2021-09-12
 * ===============================================================
 */
package com.suwon.toy.moving.out.admin.configuration;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@EnableWebSecurity
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AdminServerProperties adminServerProperties;

    public AdminSecurityConfig(AdminServerProperties adminServerProperties){
        this.adminServerProperties = adminServerProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String contextPath = adminServerProperties.getContextPath();
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();

        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl("/");
        http.csrf().disable()
                .formLogin()
                .successHandler(successHandler)
                .and()
                .logout().logoutUrl(contextPath +"/logout").and()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(contextPath + "/assets/**").permitAll()
                .antMatchers(contextPath +"/api/ping").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(contextPath +"/instances").permitAll()
                .anyRequest().authenticated();
    }
}
