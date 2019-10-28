package com.example.yeop.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeop.entity.Finance;
import com.example.yeop.entity.Institute;
import com.example.yeop.service.FinanceService;
import com.example.yeop.service.InstituteService;
import com.example.yeop.utils.CSVUtil;
import com.example.yeop.utils.RandomUtil;

@RestController
@RequestMapping(value = "/finance")
public class FinanceController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private InstituteService instituteService;
	
	@Autowired
	private FinanceService financeService;
	
	private final int MAX_CUL_SIZE = 11;
	
	
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/init")
	public @ResponseBody String initData() {
		String path = "csvdata/3.csv";
		List<List<String>> datas;
		try {
			datas = CSVUtil.readCSVFile(path);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
			return "ERROR : File not found.";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "ERROR : Unknown error";
		}		
		logger.info("#### datas : {}", datas);
		
		// Institute 세팅
		// 초기화
		instituteService.deleteAll();
		
		List<String> ins_code_rows = new ArrayList<String>(datas.get(0));
		for(int i=2; i<MAX_CUL_SIZE; i++) {
			// 문자열 자르고 금융기관 명 세팅.
			String[] split_data = datas.get(0).get(i).split("1|\\(");
			String institute_name = split_data[0];
			// 네자리수 랜덤 숫자를 가지고 institute code 세팅.
			int rand_num = RandomUtil.getRandNum(1000, 9999);
			String institute_code = "bnk" + rand_num;
			
			Institute institute = new Institute(institute_name, institute_code);
			instituteService.create(institute);
			ins_code_rows.set(i, institute_code);
		}
		logger.info("#### ins_code_rows : {}", ins_code_rows);
		logger.info("#### institute getAll : {}", instituteService.getAll());
		
		// Finance
		// 초기화
		financeService.deleteAll();
		
		List<Finance> fin_list = new ArrayList<>();
		for(int i=1; i<datas.size(); i++) {
			List<String> rows = datas.get(i);
			Integer year = Integer.parseInt(rows.get(0));
			Integer month = Integer.parseInt(rows.get(1));
			
			for(int j=2; j<MAX_CUL_SIZE; j++) {
				Integer value = Integer.parseInt(rows.get(j));
				Finance finance = new Finance(year, month, ins_code_rows.get(j), value);
				fin_list.add(finance);
			}
		}
		financeService.createAll(fin_list);
		
		return "SUCCESS";
	}
	
	
}
