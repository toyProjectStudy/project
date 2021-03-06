/**
 * ===============================================================
 * File name : EmployeeControllerTest.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.employee;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.suwon.toy.moving.out.api.employee.service.EmployeeService;
import com.suwon.toy.moving.out.common.configuration.EnableMockMvc;
import com.suwon.toy.moving.out.common.employee.Employee;

@SpringBootTest
@EnableMockMvc
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

    private static final Pageable testPageable = PageRequest.of(0,10);
    
    public static final String testEmployee = "{\n" +
            "\t\"empName\":\"jay\",\n" +
            "\t\"profile\":\"profile\",\n" +
            "\t\"teamCd\":\"01\",\n" +
            "\t\"hireDate\":\"2021-09-11T10:00:00\",\n" +
            "\t\"empEmail\":\"dlswp113@gmail.com\"\n" +
            "}";
    
    @Test
    @DisplayName("Employee ??????(pagable) ?????? ?????????")
    @WithMockUser(username = "tester", roles = {"USER", "ADMIN"})
    public void getEmployeeInfoList_success() throws Exception{
    	Page<Employee> pageList = new PageImpl<Employee>(testEmployeeList);
        given(employeeService.getEmployeeInfoList(testPageable)).willReturn(pageList);

        mockMvc.perform(get("/api/employee/list")
				.param("page", "0")
				.param("size","10"))
        		.andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Employee ??????(List) ?????? ?????????")
    @WithMockUser(username = "tester", roles = {"USER", "ADMIN"})
    public void getEmployeeList_success() throws Exception{
        given(employeeService.getEmployeeList()).willReturn(testEmployeeList);

        mockMvc.perform(get("/api/employee/allList")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }


    @Test
    @DisplayName("Admin ????????? ?????? ???????????? ?????? ?????? ????????? ????????? ????????? ?????????.")
    @WithMockUser(username = "tester", roles = {"USER", "ADMIN"})
    public void insertNewEmployee_success() throws Exception {
        given(employeeService.getEmployeeList()).willReturn(testEmployeeList);

        mockMvc.perform(post("/api/employee/insert")
                        .content(testEmployee)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Admin ????????? ?????? ???????????? ?????? ?????? ?????? ????????? ??????????????? - ?????? ?????? ??????.")
    @WithMockUser(username = "tester", roles = {"USER"})
    public void insertNewEmployee_not_permitted_user() throws Exception {
        given(employeeService.getEmployeeList()).willReturn(testEmployeeList);

        /**
         *  Method Security ????????? ??????, SecurityConfig?????? ????????? AccessDeniedHandler??? ?????? ??????,
         *  ControllerAdvice??? ????????? ExceptionHandler??? ?????? ??????.
         *
         *  ?????????, @PreAuthorized??? API ??????????????? ?????? ?????? ?????? ExceptionHandler??? AccessDeniedException Handler??? ????????????.
         */
        mockMvc.perform(post("/api/employee/insert")
                        .content(testEmployee)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isForbidden()); // 403 Forbidden
    }
}
