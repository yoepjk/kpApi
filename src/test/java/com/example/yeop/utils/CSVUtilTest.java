package com.example.yeop.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.opencsv.CSVReader;

public class CSVUtilTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void readCSVFile() throws Exception {
		String path = "csvdata/3.csv";
    	ClassPathResource cpr = new ClassPathResource(path);
    	List<List<String>> datas = new ArrayList<List<String>>();
		try {
			InputStreamReader readFile = new InputStreamReader(cpr.getInputStream(), "EUC-KR"); 
			CSVReader csvReader = new CSVReader(readFile);
			
			String[] reads;
			while ((reads = csvReader.readNext()) != null) {
				datas.add(Arrays.asList(reads));
	    	}
		} catch (FileNotFoundException e) {
			throw e;
		}
		
		logger.info("#### read file : {}", datas);
	}
}
