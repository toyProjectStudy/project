/**
 * ===============================================================
 * File name : MovingUser.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.domain.movinguser;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_USER")
@Getter
@Setter
public class MovingUser {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name="NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PHONE")
    private String phone;

    @Column(name="PASSWORD")
    private String password;
}
