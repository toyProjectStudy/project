/**
 * ===============================================================
 * File name : MovingUser.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.movinguser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="TB_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany
    @JoinTable(
            name="TB_USER_AUTHORITY",
            joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name="AUTHORITY_NAME", referencedColumnName = "AUTHORITY_NAME")})
    private Set<Authority> authoritySet;
}
