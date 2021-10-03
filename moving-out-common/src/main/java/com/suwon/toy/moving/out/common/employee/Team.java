/**
 * ===============================================================
 * File name : Team.java
 * Created by injeahwang on 2021-10-02
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_EMPLOYEE_TEAM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @Column(name = "team_id")
    private long teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_category")
    private String teamCategory;

    @Column(name = "team_description")
    private String teamDescription;
}
