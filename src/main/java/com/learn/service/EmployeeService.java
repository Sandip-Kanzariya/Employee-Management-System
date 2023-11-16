package com.learn.service;

import java.util.List;

import com.learn.entities.Department;
import com.learn.entities.Employee;

public interface EmployeeService {
	
	Employee getEmployeeById(Long id);
	
	List<Employee> getEmployeeByDeptid(Department d);
	
	List<Employee> getAll();
}
