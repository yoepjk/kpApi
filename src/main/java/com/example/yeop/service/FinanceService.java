package com.example.yeop.service;

import java.util.List;

import com.example.yeop.entity.Finance;

public interface FinanceService {
	public void createAll(List<Finance> finance_list);
	public List<Finance> getAll();
	public void deleteAll();
}
