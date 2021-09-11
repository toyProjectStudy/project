package com.suwon.toy.moving.out.api.employee.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwon.toy.moving.out.common.employee.Employee;
import com.suwon.toy.moving.out.common.employee.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional(readOnly = true)
	public List<Employee> getEmployeeInfoList() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Employee> getEmployeeInfo(String empName, Pageable pageable) {

		return employeeRepository.findByEmpNameContaining(empName,pageable);
	}

	public Employee insertEmployee(@Valid Employee employeeDto) {

		Employee emp = new Employee();
		emp.setEmpId(employeeDto.getEmpId());
		emp.setEmpEmail(emp.getEmpEmail());
		emp.setEmpName(employeeDto.getEmpName());
		emp.setHireDate(employeeDto.getHireDate());
		emp.setProfile(employeeDto.getProfile());
		emp.setTeamCd(employeeDto.getTeamCd());
		
		return employeeRepository.save(emp);
	}


}

