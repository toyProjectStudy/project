/**
 * ===============================================================
 * File name : Employee.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.employee;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_EMPLOYEE")
@Getter
@Setter
@SequenceGenerator(
        name="SEQ_EMPLOYEE_GEN",
        sequenceName="EMPLOYEE_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
public class Employee {

    @Id
    @Column(name = "EMP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPLOYEE_GEN")
    private Long empId; // TODO : EMP_ID는 SEQUENCE를 이용해서 발번하는 PK인지 확인필요(Oracle은 SEQUENCE가 필수)

    @Column(name="EMP_NAME")
    private String empName;

    @Column(name="PROFILE")
    private String profile;

    @Column(name = "TEAM_CD")
    private String teamCd;

    @Column(name="HIRE_DATE")
    private LocalDateTime hireDate;

    @Column(name="EMP_EMAIL")
    private String empEmail;

}
