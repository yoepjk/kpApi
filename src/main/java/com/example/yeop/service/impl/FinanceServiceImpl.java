package com.example.yeop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.yeop.entity.Finance;
import com.example.yeop.repository.FinanceRepository;
import com.example.yeop.service.FinanceService;
import com.example.yeop.vo.TotalByYear;


@Service("FinanceService")
public class FinanceServiceImpl implements FinanceService {
	@Autowired private FinanceRepository finRepository;
	
	public void createAll(List<Finance> finance_list) {
		finRepository.saveAll(finance_list);
		finRepository.flush();
	}
	
	public List<Finance> getAll() {
		return finRepository.findAll();
	}
	
	public List<TotalByYear> getTotalByYear() {
		return finRepository.findTotalByYear();
	}
	
	public List<TotalByYear> getTotalByYearAndName(String bank_name, Integer start_year, Integer end_year) {
		return finRepository.findTotalByYearAndName(bank_name, start_year, end_year);
	}
	
	public void deleteAll() {
		finRepository.deleteAll();
	}
	
}
