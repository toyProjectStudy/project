/**
 * ===============================================================
 * File name : EmployeeControllerTest.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.employee;

import com.suwon.toy.moving.out.api.configuration.SecurityConfig;
import com.suwon.toy.moving.out.api.employee.controller.EmployeeController;
import com.suwon.toy.moving.out.api.employee.service.EmployeeService;
import com.suwon.toy.moving.out.common.employee.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    private static final List<Employee> testEmployeeList = List.of(
            new Employee(1L, "emp1","profile", "teamcd", LocalDateTime.now(), "phone"),
            new Employee(2L, "emp2","profile", "teamcd", LocalDateTime.now(), "phone"),
            new Employee(3L, "emp3","profile", "teamcd", LocalDateTime.now(), "phone"),
            new Employee(4L, "emp4","profile", "teamcd", LocalDateTime.now(), "phone")
            );

    public static final String testEmployee = "{\n" +
            "\t\"empName\":\"jay\",\n" +
            "\t\"profile\":\"profile\",\n" +
            "\t\"teamCd\":\"01\",\n" +
            "\t\"hireDate\":\"2021-09-11T10:00:00\",\n" +
            "\t\"empEmail\":\"dlswp113@gmail.com\"\n" +
            "}";
    @Test
    @WithMockUser(username = "tester", roles = {"USER", "ADMIN"})
    public void getEmployeeInfoList_success() throws Exception{
        given(employeeService.getEmployeeInfoList()).willReturn(testEmployeeList);

        mockMvc.perform(get("/api/employee/list")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Employee 조회(pagable) 성공 테스트")
    @WithMockUser(username = "tester", roles = {"USER", "ADMIN"})
    public void getEmployeeInfo_success() throws Exception {
        given(employeeService.getEmployeeInfo(any(),any())).willReturn(testEmployeeList);

        mockMvc.perform(get("/api/employee/anyone")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Admin 권한이 있는 사용자의 경우 신규 구성원 등록이 되어야 합니다.")
    @WithMockUser(username = "tester", roles = {"USER", "ADMIN"})
    public void insertNewEmployee_success() throws Exception {
        given(employeeService.getEmployeeInfo(any(),any())).willReturn(testEmployeeList);

        mockMvc.perform(post("/api/employee/insert")
                        .content(testEmployee)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Admin 권한이 있는 사용자의 경우 신규 구성원 등록이 되어야 합니다.")
    @WithMockUser(username = "tester", roles = "USER")
    public void insertNewEmployee_not_permitted_user() throws Exception {
        given(employeeService.getEmployeeInfo(any(),any())).willReturn(testEmployeeList);

        mockMvc.perform(post("/api/employee/insert")
                        .content(testEmployee)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isForbidden()); // 403 Forbidden
    }
}
