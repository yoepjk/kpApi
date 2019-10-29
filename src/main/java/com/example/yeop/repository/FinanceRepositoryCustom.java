package com.example.yeop.repository;

import java.util.List;
import com.example.yeop.vo.TotalByYear;


public interface FinanceRepositoryCustom {
	List<TotalByYear> findTotalByYear();
	List<TotalByYear> findTotalByYearAndName(String bank_name, Integer start_year, Integer end_year);
}
