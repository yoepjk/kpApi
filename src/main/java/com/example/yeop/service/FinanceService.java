package com.example.yeop.service;

import java.util.List;

import com.example.yeop.entity.Finance;
import com.example.yeop.vo.TotalByYear;

public interface FinanceService {
	public void createAll(List<Finance> finance_list);
	public List<Finance> getAll();
	public List<TotalByYear> getTotalByYear();
	public void deleteAll();
}
