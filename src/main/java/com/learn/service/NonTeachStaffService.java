package com.learn.service;

import java.util.List;

import com.learn.entities.NonTeachingStaff;

public interface NonTeachStaffService {

	public void addNonTeachStaff(NonTeachingStaff nt);

	public List<NonTeachingStaff> getAll();
	
	public void deleteNonTeachStaff(Long id);
	
	public NonTeachingStaff getNonById(Long id);
	
	public NonTeachingStaff updateNon(NonTeachingStaff n);
}
