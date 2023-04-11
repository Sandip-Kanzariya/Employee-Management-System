package com.learn.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.entities.Department;
import com.learn.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository dRepo;
	
	@Override
	public void addDepartment(Department department) {
		dRepo.save(department);
	}

	@Override
	public List<Department> getDepartments() {

//		Pageable pages = PageRequest.of(1, 3, Direction.DESC, "dept_id");
		
//		return dRepo.findAll(pages);
		
		List <Department> list = dRepo.findAll();
		
		Collections.sort(list, new Comparator<Department>() {
            @Override
            public int compare(Department p1, Department p2) {
            	
            	Long d = p1.getDept_id() - p2.getDept_id();
            	int r = 0;
            	
            	if(d < 0) r = -1;
            	else if(d > 0) r = 1;
      
            	return r;
            }
        });
		
		return list;
	}
	
	@Override
	public void deleteDepartment(Long id) {
		dRepo.deleteById(id);
	}

	@Override
	public Department getDepartment(Long id) {
	
		Optional<Department> op = dRepo.findById(id);
		return op.get();
	}

	@Override
	public Department updateDepartment(Department d) {
		return dRepo.save(d);
	}
	
	
}
