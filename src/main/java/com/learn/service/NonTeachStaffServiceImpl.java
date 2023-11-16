package com.learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.entities.NonTeachingStaff;
import com.learn.repository.NonTeachSatffRepository;

@Service
public class NonTeachStaffServiceImpl implements NonTeachStaffService {

	@Autowired
	private NonTeachSatffRepository nRepo;


	@Override
	public void addNonTeachStaff(NonTeachingStaff nt) {
		nRepo.save(nt);
	}


	@Override
	public List<NonTeachingStaff> getAll() {
		return nRepo.findAll();
	}


	@Override
	public void deleteNonTeachStaff(Long id) {
		nRepo.deleteById(id);
	}


	@Override
	public NonTeachingStaff getNonById(Long id) {
		Optional<NonTeachingStaff> o = nRepo.findById(id);
		return o.get();
	}


	@Override
	public NonTeachingStaff updateNon(NonTeachingStaff n) {
		return nRepo.save(n);
	}

}
