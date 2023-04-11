package com.learn.service;

import java.util.List;

import com.learn.entities.NonTeachingStaff;

public interface NonTeachStaffService {

		public void addNonTeachStaff(NonTeachingStaff nt);
		
		public List<NonTeachingStaff> getAll();
		
		
}
