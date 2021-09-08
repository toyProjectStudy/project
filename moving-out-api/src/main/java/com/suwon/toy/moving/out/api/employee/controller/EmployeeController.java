package com.suwon.toy.moving.out.api.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suwon.toy.moving.out.api.employee.service.EmployeeService;
import com.suwon.toy.moving.out.common.employee.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employee/list")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Employee>> getEmployeeInfoList(){
		return ResponseEntity.ok(employeeService.getEmployeeInfoList());
	}
	
	@GetMapping("/employee/{empName}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<List<Employee>> getEmployeeInfo(@PathVariable String empName){
		Pageable pageable = PageRequest.of(0,3);
		return ResponseEntity.ok(employeeService.getEmployeeInfo(empName,pageable));
	}

	@PostMapping("/employee/insert")
    @PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employeeDto){
		return ResponseEntity.ok(employeeService.insertEmployee(employeeDto));
	}
	
} 
	