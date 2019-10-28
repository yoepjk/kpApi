package com.example.yeop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeop.entity.Institute;
import com.example.yeop.repository.InstituteRepository;
import com.example.yeop.service.InstituteService;


@Service("InstituteService")
public class InstituteServiceImpl implements InstituteService {
	@Autowired
	private InstituteRepository insRepository;
	
	public Institute create(Institute institute) {
		return insRepository.save(institute);	
	}
	
	public List<Institute> getAll() {
		return insRepository.findAll();
	}

	public void deleteAll() {
		insRepository.deleteAll();
	}
	
}
