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
import com.learn.service.NonTeachStaffService;

@Controller
public class NonTeachingStaffController {

	@Autowired
	private NonTeachStaffService nService;
	
	@Autowired
	private DepartmentServiceImpl dService;

	@Autowired
	private EmployeeServiceImpl eService;

	@GetMapping("/nonteach-form")
	public String addNonTeachPage(ModelMap departmentModel) {
		List<Department> list = dService.getDepartments();
		departmentModel.addAttribute("departmentlist", list);

		return "nonteachform";
	}

	@PostMapping("/nsave-emp")
	public String addEmployee(@RequestParam(value = "ename", required = true) String ename,
			@RequestParam(value = "email", required = true) String uemail,
			@RequestParam(value = "salary", required = true) Double salary,
			@RequestParam(value = "role", required = true) String role,
			@RequestParam(value = "dno", required = true) Long dno, ModelMap emloyeeModel) {

		NonTeachingStaff ts = new NonTeachingStaff();

		ts.setName(ename);
		ts.setEmail(uemail);
		ts.setSalary(salary);
		ts.setRole(role);

		Department d = dService.getDepartment(dno);
		ts.setDepartment(d);

		nService.addNonTeachStaff(ts);

		return "redirect:/allnonteach";
	}

	@GetMapping("/allnonteach")
	public String allnonteach(ModelMap nonteachModel) {
		List<NonTeachingStaff> nlist = nService.getAll();
		nonteachModel.addAttribute("nlist", nlist);

		return "allnonteach";
	}

	@GetMapping("/nonteach-list/{id}")
	public String nonteachlist(@PathVariable Long id, ModelMap nonteachModel) {
		Department d = dService.getDepartment(id);
		String dname = d.getName();

		List<Employee> el = eService.getEmployeeByDeptid(d);

		List<Employee> reqnonteach = new ArrayList<>();

		List<NonTeachingStaff> ntel = nService.getAll();

		for (NonTeachingStaff nt : ntel) {
			for (Employee e : el) {
				if (nt.getEmpId() == e.getEmpId()) {
					reqnonteach.add(e);
					break;
				}
			}
		}

		nonteachModel.addAttribute("reqnonteach", reqnonteach);
		nonteachModel.addAttribute("dname", dname);
		nonteachModel.addAttribute("did", id);

		return "nonteachlist";
	}

	// Delete Employee
	@GetMapping("/delete-nonteach/{id}")
	public String deleteNonTeach(@PathVariable Long id) {

		nService.deleteNonTeachStaff(id);

		return "redirect:/allnonteach";
	}

	@GetMapping("/edit-non/{id}")
	public String updatePage(@PathVariable("id") Long id, ModelMap NonTeachModel) {
		NonTeachModel.addAttribute("id", id);
		NonTeachingStaff nte = nService.getNonById(id);

		List<Department> list = dService.getDepartments();

		NonTeachModel.addAttribute("departmentlist", list);

		NonTeachModel.addAttribute("nonteach", nte);


		return "updatenonteach";
	}


	@PostMapping("/updated-non")
	public String updateNon(@RequestParam(value = "id") Long id,
			@RequestParam(value = "ename", required = true) String ename,
			@RequestParam(value = "email", required = true) String uemail,
			@RequestParam(value = "salary", required = true) Double salary,
			@RequestParam(value = "role", required = true) String role,
			@RequestParam(value = "dno", required = true) Long dno,

			ModelMap departmentModel) {

		Department d = dService.getDepartment(dno);

		NonTeachingStaff nte = nService.getNonById(id);
		nte.setName(ename);
		nte.setEmail(uemail);
		nte.setSalary(salary);
		nte.setRole(role);
		nte.setDepartment(d);
		nte = nService.updateNon(nte);

		return "redirect:/allnonteach";
	}
	
	//	For Save employee per departments
	@GetMapping("/nonteach-form-per-dept/{id}")
	public String addNonTeachPerDept(@PathVariable("id") Long did, ModelMap perDeptNonTeachModel){

		Department d = dService.getDepartment(did);

		perDeptNonTeachModel.addAttribute("deptper", d);

		return "nonteachformperdept";
	}
	
}
