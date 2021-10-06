package com.suwon.toy.moving.out.api.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suwon.toy.moving.out.api.employee.service.EmployeeService;
import com.suwon.toy.moving.out.common.common.annotation.security.ApplyAdminRole;
import com.suwon.toy.moving.out.common.common.annotation.security.ApplyUserAdminRole;
import com.suwon.toy.moving.out.common.employee.Employee;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@ApiOperation(value = "직원 목록 조회(Paging)(User, Admin 공통)")
	@GetMapping("/employee/list")
	@ApplyUserAdminRole
    public ResponseEntity<Page<Employee>> getEmployeeInfoList(Pageable pageable){
		return ResponseEntity.ok(employeeService.getEmployeeInfoList(pageable));
	}

	@ApiOperation(value = "직원 성명으로 조회(User, Admin 공통)")
	@GetMapping("/employee/{empName}")
	@ApplyUserAdminRole
	public ResponseEntity<Page<Employee>> getEmployeeInfo(@PathVariable String empName,Pageable pageable){
		return ResponseEntity.ok(employeeService.getEmployeeInfo(empName,pageable));
	}

	@ApiOperation(value = "직원 등록(Admin 전용)")
	@PostMapping("/employee/insert")
    @ApplyAdminRole
	public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employeeDto){
		return ResponseEntity.ok(employeeService.insertEmployee(employeeDto));
	}
	
	@ApiOperation(value = "직원 목록 List 조회(User, Admin 공통)")
	@GetMapping("/employee/allList")
	@ApplyUserAdminRole
    public ResponseEntity<List<Employee>> getEmployeeList(){
		return ResponseEntity.ok(employeeService.getEmployeeList());
	}
	
	
} 
	