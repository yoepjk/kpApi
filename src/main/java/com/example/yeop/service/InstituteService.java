package com.example.yeop.service;

import java.util.List;
import com.example.yeop.entity.Institute;


public interface InstituteService {
	public Institute create(Institute institute);
	public List<Institute> getAll();
	public void deleteAll();
}
