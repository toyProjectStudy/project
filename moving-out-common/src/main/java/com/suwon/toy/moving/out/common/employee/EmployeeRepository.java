/**
 * ===============================================================
 * File name : EmployeeRepository.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.employee;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByEmpNameContaining(String empName, Pageable pageable);

	void deleteByEmpId(Long empId);

	boolean findOneByEmpId(Long empId);


}
