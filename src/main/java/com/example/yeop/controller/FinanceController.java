package com.example.yeop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.yeop.service.FinanceService;
import com.example.yeop.service.InstituteService;
import com.example.yeop.vo.TotalByYear;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/finance")
public class FinanceController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private InstituteService instituteService;
	@Autowired private FinanceService financeService;
	@Autowired private ObjectMapper objectMapper;
	

	@RequestMapping(method = RequestMethod.GET, value = "/total-year")
	public @ResponseBody String totalByYear() {
		List<TotalByYear> total_list = financeService.getTotalByYear();
		logger.info("#### total : {}", total_list);
		
		Map<Integer, Integer> total_amount = new HashMap<>();
		Map<Integer, Map<String, Integer>> detail_amount = new HashMap<>();
		
		for(TotalByYear total : total_list) {
			Integer key = total.getYear();
			
			Integer sum = (Integer) total_amount.getOrDefault(key, 0) + total.getValue();
			total_amount.put(key, sum);	
			
			if(!detail_amount.containsKey(key)) {
				detail_amount.put(key, new HashMap<String, Integer>());
			}
			
			detail_amount.get(key).put(total.getName(), total.getValue());
		}
		logger.info("#### total_amount : {}", total_amount);
		logger.info("#### detail_amount : {}", detail_amount);
		
		List<Map<String, Object>> data_list = new ArrayList<>();
		for(Integer year : total_amount.keySet()) {
			Map<String, Object> map = new HashMap<>();
			map.put("year", year);
			map.put("total_amount", total_amount.get(year));
			map.put("detail_amount", detail_amount.get(year));
			data_list.add(map);
		}
		
		Map<String, Object> result_map = new HashMap<>();
		result_map.put("name", "주택금융 공급현황");
		result_map.put("data", data_list);
		
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result_map);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return "ERROR : Json processing error";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/max-year")
	public @ResponseBody String maxByYear() {
		List<TotalByYear> total_list = financeService.getTotalByYear();
		logger.info("#### total : {}", total_list);
		
		TotalByYear max_total = new TotalByYear(2005, "", 0);
		for(TotalByYear total : total_list) {
			if(max_total.getValue() < total.getValue()) {
				max_total.setYear(total.getYear());
				max_total.setName(total.getName());
				max_total.setValue(total.getValue());
			}
		}
		logger.info("#### after total : {}", total_list);
		logger.info("#### maxTotal : {}", max_total);
		
		Map<String, Object> result_map = new HashMap<>();
		result_map.put("year", max_total.getYear());
		result_map.put("bank", max_total.getName());
		
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result_map);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return "ERROR : Json processing error";
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/avg-minmax-year")
	public @ResponseBody String averageMinMax() {
		String bank_name = "외환은행";
		Integer start = 2005;
		Integer end = 2016;
		
		List<TotalByYear> total_list = financeService.getTotalByYearAndName(bank_name, start, end);
		logger.info("#### total : {}", total_list);
		TotalByYear min_total = new TotalByYear(2005, bank_name, 9999999);
		TotalByYear max_total = new TotalByYear(2005, bank_name, 0);
		
		for(TotalByYear total : total_list) {
			Integer avg = Math.round((float)total.getValue() / 12);
			// min 값 구하기
			if(min_total.getValue() > avg) {
				min_total.setValue(avg);
				min_total.setYear(total.getYear());
			}
			// max 값 구하기
			if(max_total.getValue() < avg) {
				max_total.setValue(avg);
				max_total.setYear(total.getYear());
			}
		}
		logger.info("#### max : {},  min : {}", max_total, min_total);
		
		List<Map<String, Integer>> avg_list = new ArrayList<>();
		Map<String, Integer> min_map = new HashMap<String, Integer>();
		min_map.put("year", min_total.getYear());
		min_map.put("amount", min_total.getValue());
		
		Map<String, Integer> max_map = new HashMap<String, Integer>();
		max_map.put("year", max_total.getYear());
		max_map.put("amount", max_total.getValue());
		
		avg_list.add(min_map);
		avg_list.add(max_map);
		
		Map<String, Object> result_map = new HashMap<>();
		result_map.put("bank", bank_name);
		result_map.put("support_amount", avg_list);
		
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result_map);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return "ERROR : Json processing error";
		}
	}
	
	
}
