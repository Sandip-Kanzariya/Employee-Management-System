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
import com.learn.entities.TeachingStaff;
import com.learn.service.DepartmentService;
import com.learn.service.EmployeeService;
import com.learn.service.TeachStaffService;

@Controller
public class TeachingStaffController {

	@Autowired
	private TeachStaffService tService;

	@Autowired
	private DepartmentService dService;

	@Autowired
	private EmployeeService eService;

	@GetMapping("/teach-form")
	public String addTeachPage(ModelMap departmentModel) {
		List<Department> list = dService.getDepartments();
		departmentModel.addAttribute("departmentlist", list);

		return "teachform";
	}

	@PostMapping("/save-emp")
	public String addEmployee(@RequestParam(value = "ename", required = true) String ename,
			@RequestParam(value = "email", required = true) String uemail,
			@RequestParam(value = "salary", required = true) Double salary,
			@RequestParam(value = "subject", required = true) String subject,
			@RequestParam(value = "dno", required = true) Long dno, ModelMap emloyeeModel) {

		TeachingStaff ts = new TeachingStaff();

		ts.setName(ename);
		ts.setEmail(uemail);
		ts.setSalary(salary);
		ts.setSubject(subject);

		Department d = dService.getDepartment(dno);
		ts.setDepartment(d);

		tService.addTeachStaff(ts);

		return "redirect:/allteach";
	}

	@GetMapping("/allteach")
	public String allteach(ModelMap teachModel){
		List<TeachingStaff> tlist = tService.getAll();
		teachModel.addAttribute("tlist", tlist);

		return "allteach";
	}

	@GetMapping("/teach-list/{id}")
	public String teachlist(@PathVariable Long id, ModelMap teachModel) {
		Department d = dService.getDepartment(id);

		String dname = d.getName();


		List<Employee> el = eService.getEmployeeByDeptid(d);

		List<Employee> reqteach = new ArrayList<>();

		List<TeachingStaff> tel = tService.getAll();

		for (TeachingStaff t : tel) {
			for (Employee e : el) {
				if (t.getEmpId() == e.getEmpId()) {
					reqteach.add(e);
					break;
				}
			}
		}

		teachModel.addAttribute("reqteach", reqteach);
		teachModel.addAttribute("dname", dname);
		teachModel.addAttribute("did", id);

		return "teachlist";
	}

	// Delete Employee
	@GetMapping("/delete-teach/{id}")
	public String deleteTeach(@PathVariable Long id){

		tService.deleteTeachStaff(id);

		return "redirect:/allteach";
	}


	@GetMapping("/edit-teach/{id}")
	public String updatePage(@PathVariable("id") Long id, ModelMap TeachModel) {
		TeachModel.addAttribute("id", id);
		TeachingStaff te = tService.getTeachById(id);

		List<Department> list = dService.getDepartments();

		TeachModel.addAttribute("departmentlist", list);

		TeachModel.addAttribute("teach", te);


		return "updateteach";
	}


	@PostMapping("/updated-teach")
	public String updateTeach(@RequestParam(value = "id") Long id,
			@RequestParam(value = "ename", required = true) String ename,
			@RequestParam(value = "email", required = true) String uemail,
			@RequestParam(value = "salary", required = true) Double salary,
			@RequestParam(value = "subject", required = true) String subject,
			@RequestParam(value = "dno", required = true) Long dno,

			ModelMap departmentModel) {

		Department d = dService.getDepartment(dno);

		TeachingStaff te = tService.getTeachById(id);
		te.setName(ename);
		te.setEmail(uemail);
		te.setSalary(salary);
		te.setSubject(subject);
		te.setDepartment(d);
		te = tService.updateTeach(te);

		return "redirect:/allteach";
	}


	//	For Save employee per departments
	@GetMapping("/teach-form-per-dept/{id}")
	public String addTeachPerDept(@PathVariable("id") Long did, ModelMap perDeptTeachModel){

		Department d = dService.getDepartment(did);

		perDeptTeachModel.addAttribute("deptper", d);

		return "teachformperdept";
	}




}
