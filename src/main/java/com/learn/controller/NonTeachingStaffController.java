package com.learn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.entities.Department;
import com.learn.entities.Employee;
import com.learn.entities.NonTeachingStaff;
import com.learn.service.DepartmentServiceImpl;
import com.learn.service.EmployeeServiceImpl;
import com.learn.service.NonTeachStaffServiceImpl;

@Controller
public class NonTeachingStaffController {
	
	@Autowired
	private NonTeachStaffServiceImpl nService;
	
	@Autowired
	private DepartmentServiceImpl dService;
	
	@Autowired
	private EmployeeServiceImpl eService;
	
	
	@GetMapping("/nonteach-form")
	public String addNonTeachPage(ModelMap departmentModel){
		List<Department> list = dService.getDepartments();
		departmentModel.addAttribute("departmentlist", list);
		
		return "nonteachform";
	}

	@PostMapping("/nsave-emp")
	public String addEmployee(@RequestParam(value = "ename", required = true) String ename, @RequestParam(value = "email", required = true) String uemail, 
			@RequestParam(value = "salary", required = true) Double salary ,@RequestParam(value = "role", required = true) String role,@RequestParam(value = "dno", required = true) Long dno, ModelMap emloyeeModel){
		
		NonTeachingStaff ts = new NonTeachingStaff();
		
		ts.setName(ename);
		ts.setEmail(uemail);
		ts.setSalary(salary);
		ts.setRole(role);
		
		Department d = dService.getDepartment(dno);
		ts.setDepartment(d);
		
		nService.addNonTeachStaff(ts);	
			
		return "redirect:/nonteach-form";
	}
	

	
	@GetMapping("/nonteach-list/{id}")
	public String nonteachlist(@PathVariable Long id, ModelMap nonteachModel){
		
		Department d = dService.getDepartment(id);
		
//		List <Employee> el = eService.getEmployeeByDeptid(d);
		
		
		List <Employee> reqnonteach = new ArrayList<>();
		
		List <NonTeachingStaff> ntel = nService.getAll();
		
		for(NonTeachingStaff nt : ntel) {
			Employee e = eService.getEmployeeByIdAndDepartment(nt.getEmp_id(),d); 
			reqnonteach.add(e);
		}
		
		
		nonteachModel.addAttribute("reqnonteach", reqnonteach);
		
		return "nonteachlist";
	}
	
//	@GetMapping("/nonteachlist")
//	public 
	
}

