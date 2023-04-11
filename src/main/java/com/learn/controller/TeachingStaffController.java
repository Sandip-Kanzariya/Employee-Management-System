package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.entities.Department;
import com.learn.entities.TeachingStaff;
import com.learn.service.DepartmentServiceImpl;
import com.learn.service.TeachStaffServiceImpl;

@Controller
//@RequestMapping("/employee")
public class TeachingStaffController {
	
	@Autowired
	private TeachStaffServiceImpl tService;
	
	@Autowired
	private DepartmentServiceImpl dService;
	
	@GetMapping("/teach-form")
	public String addTeachPage(ModelMap departmentModel){
		List<Department> list = dService.getDepartments();
		departmentModel.addAttribute("departmentlist", list);

	
		return "teachform";
	}
	
	@PostMapping("/save-emp")
	public String addEmployee(@RequestParam(value = "ename", required = true) String ename, @RequestParam(value = "email", required = true) String uemail, 
			@RequestParam(value = "salary", required = true) Double salary ,@RequestParam(value = "subject", required = true) String subject,@RequestParam(value = "dno", required = true) Long dno, ModelMap emloyeeModel){
		
		TeachingStaff ts = new TeachingStaff();
		
		ts.setName(ename);
		ts.setEmail(uemail);
		ts.setSalary(salary);
		ts.setSubject(subject);
		
		Department d = dService.getDepartment(dno);
		ts.setDepartment(d);
		
		tService.addTeachStaff(ts);	
			
		return "redirect:/teach-form";
	}
	
	/*
	
	@GetMapping("/delete/{empid}")
	public void deleteEmployee(@PathVariable("empid") Long id){
		
	}

*/
	
}
