package com.evolent.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evolent.employee.datamodel.Employee; 

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Boolean existsByEmail(String email);
	Employee findByIdAndStatus(Integer id,Boolean status);
}
