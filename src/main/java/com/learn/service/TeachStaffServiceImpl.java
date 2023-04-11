package com.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.entities.Department;
import com.learn.entities.TeachingStaff;
import com.learn.repository.TeachStaffRepository;

@Service
public class TeachStaffServiceImpl implements TeachStaffService {
	
	@Autowired
	private TeachStaffRepository tRepo;
	
	@Override
	public void addTeachStaff(TeachingStaff ts) {
		tRepo.save(ts);
	}
	
	
	
}
