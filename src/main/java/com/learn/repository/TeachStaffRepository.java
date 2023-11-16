package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.entities.TeachingStaff;

public interface TeachStaffRepository extends JpaRepository<TeachingStaff, Long> {
	
}
