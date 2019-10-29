package com.example.yeop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public @ResponseBody String testApi() {
		String path = "csvdata/3.csv";
		try {
	    	ClassPathResource cpr = new ClassPathResource(path);
	    	logger.info(cpr.getURI().toString());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);;
			return "FAIL";
		}
		 
		return "hahahaha";
	}
	
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
			return objectMapper.writeValueAsString(result_map);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return "ERROR : Json processing error";
		}
	}

	
	
	
}
