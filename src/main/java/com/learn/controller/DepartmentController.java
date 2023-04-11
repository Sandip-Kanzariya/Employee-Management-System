package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.entities.Department;
import com.learn.service.DepartmentServiceImpl;


@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentServiceImpl dService;
	
	@GetMapping("/")
	public String go(){
		return "index.html";
	}
	
	@GetMapping("/departments")
	public String getDepartments(ModelMap departmentModel){
		List<Department> list = dService.getDepartments();
		departmentModel.addAttribute("departmentlist", list);
		
		return "departments";
	}
	
	@GetMapping("/add-form")
	public String addDepartmentPage(){
		return "adddepartment";
	}
	
	@PostMapping("/save-dept")
	public String addDepartment(@RequestParam(value = "dname", required = true) String dname, ModelMap departmentModel) {
		Department department = new Department();
		department.setName(dname);
		dService.addDepartment(department); // DB 
		
		List<Department> list = dService.getDepartments();
		departmentModel.addAttribute("msg", "Department added successfully");
		departmentModel.addAttribute("departmentlist", list);
		
		return "redirect:/departments";
	}
	
	@GetMapping("/delete-dept/{id}")
	public String deleteDepartment(@PathVariable Long id){
		dService.deleteDepartment(id);
		return "redirect:/departments";
	}
		
	@GetMapping("/edit-dept/{id}")
	public String updatePage(@PathVariable("id") Long id, ModelMap departmentModel) {
		departmentModel.addAttribute("id", id);
		Department department = dService.getDepartment(id);
		departmentModel.addAttribute("department", department);
		return "updatedepartment";
	}
	
	
	@PostMapping("/updated")
	public String updateDepartment(@RequestParam(value = "id") Long id, @RequestParam(value = "dname") String dname, ModelMap departmentModel) {
		
		Department department = dService.getDepartment(id);
		department.setName(dname);
		
		department = dService.updateDepartment(department);
		
		return "redirect:/departments";
	}
	
}
	