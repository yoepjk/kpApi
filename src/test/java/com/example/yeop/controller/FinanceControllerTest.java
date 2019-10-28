package com.example.yeop.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.yeop.entity.Institute;
import com.example.yeop.service.InstituteService;
import com.example.yeop.utils.CSVUtil;
import com.example.yeop.utils.RandomUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
class FinanceControllerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	InstituteService instituteService;
	
	@Test
	public String initDataTest() {
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
		Map<String, String> institute_map = new HashMap<>();
		List<String> first_rows = datas.get(0);
		for(int i=2; i<10; i++) {
			// 문자열 자르고 금융기관 명 세팅.
			String[] split_data = first_rows.get(i).split("1|\\(");
			String institute_name = split_data[0];
			// 네자리수 랜덤 숫자를 가지고 institute code 세팅.
			int rand_num = RandomUtil.getRandNum(1000, 9999);
			String institute_code = "bnk" + rand_num;

			institute_map.put(institute_name, institute_code);
		}
		logger.info("#### institute_map : {}", institute_map);
		
		for(String key : institute_map.keySet()) {
			Institute institute = new Institute(key, institute_map.get(key));
			instituteService.create(institute);
        }
		
		logger.info("#### institute getAll : {}", instituteService.getAll());
		
		
		
		
		
		return "SUCCESS";
	}
}
