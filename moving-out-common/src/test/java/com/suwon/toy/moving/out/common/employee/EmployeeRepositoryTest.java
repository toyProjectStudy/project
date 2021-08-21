/**
 * ===============================================================
 * File name : EmployeeRepositoryTest.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.employee;

import com.suwon.toy.moving.out.common.configuration.MovingOutCommonConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Import(MovingOutCommonConfig.class)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("이사 센터 직원의 정보를 등록한다.")
    public void movingEmployeeRegisterTest(){
        Employee employee = new Employee();
        employee.setEmpName("김철수");
        employee.setProfile("profile");
        employee.setHireDate(LocalDateTime.now());
        employee.setEmpEmail("00000@gmail.com");
        employee.setTeamCd("00"); // TODO : Team Code 체계??

        assertDoesNotThrow(()->this.employeeRepository.save(employee));
        assertEquals(employee.getEmpId(),
                this.employeeRepository.findById(employee.getEmpId()).get().getEmpId());
    }
}
