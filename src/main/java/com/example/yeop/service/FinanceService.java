package com.example.yeop.service;

import java.util.List;

import com.example.yeop.entity.Finance;
import com.example.yeop.vo.TotalByYear;

public interface FinanceService {
	public void createAll(List<Finance> finance_list);
	public List<Finance> getAll();
	public List<TotalByYear> getTotalByYear();
	public List<TotalByYear> getTotalByYearAndName(String bank_name, Integer start_year, Integer end_year);
	public void deleteAll();
}
