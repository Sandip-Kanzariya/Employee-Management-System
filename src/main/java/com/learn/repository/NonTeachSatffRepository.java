package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.entities.NonTeachingStaff;

public interface NonTeachSatffRepository extends JpaRepository<NonTeachingStaff, Long>{
	
}
