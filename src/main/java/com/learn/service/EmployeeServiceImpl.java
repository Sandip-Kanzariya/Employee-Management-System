package com.learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.entities.Department;
import com.learn.entities.Employee;
import com.learn.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository eRepo;
	
	@Override
	public List<Employee> getEmployeeByDeptid(Department d){
		
		return eRepo.findByDepartment(d);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		
		Optional<Employee> ob = eRepo.findById(id);
		
		return ob.get();
	}

	@Override
	public Employee getEmployeeByIdAndDepartment(Long id, Department d) {
		
		Employee t = eRepo.findByEmp__id(id, d).get(0);
		System.out.println("Try : " + t);
		
		return t;
	}
	

}
