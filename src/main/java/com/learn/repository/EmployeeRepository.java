package com.learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.entities.Department;
import com.learn.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List <Employee> findByDepartment(Department d);
	
	List <Employee>findByEmp__id(Long id, Department d); 
	
}
