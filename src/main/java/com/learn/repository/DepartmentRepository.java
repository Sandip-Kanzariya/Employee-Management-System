package com.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.learn.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>, PagingAndSortingRepository<Department, Long>{
	
}
