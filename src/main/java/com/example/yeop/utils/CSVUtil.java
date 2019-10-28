package com.example.yeop.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import com.opencsv.CSVReader;


public class CSVUtil {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static List<List<String>> readCSVFile(String path) throws Exception {
    	ClassPathResource cpr = new ClassPathResource(path);
    	List<List<String>> datas = new ArrayList<List<String>>();
		try {
			FileInputStream csvFile = new FileInputStream(cpr.getFile());
			InputStreamReader readFile = new InputStreamReader(csvFile, "EUC-KR"); 
			CSVReader csvReader = new CSVReader(readFile);
			
			String[] reads;
			while ((reads = csvReader.readNext()) != null) {
				datas.add(Arrays.asList(reads));
	    	}
		} catch (FileNotFoundException e) {
			throw e;
		}
		
		return datas;
	}
}
