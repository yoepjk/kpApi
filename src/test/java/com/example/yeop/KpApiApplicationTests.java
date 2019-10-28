package com.example.yeop;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import com.example.yeop.entity.Finance;
import com.example.yeop.repository.FinanceRepository;
import com.opencsv.CSVReader;


@RunWith(SpringRunner.class)
@SpringBootTest
class KpApiApplicationTests {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FinanceRepository repository;

    @After(value = "")
    public void cleanup() {
        repository.deleteAll();
    }

    
    @Test
    public void randTest() {
    	// example
        String[] banks = {"a", "b", "c", "d", "e", "f"};
        List<String> bank_list = Arrays.asList(banks);
        
        Map<String, String> institute_map = new HashMap<>();
        List<Integer> code_list = new ArrayList<>();
        for(int i=0; i<6; i++) {
            int rand_num = (int) (Math.random() * 9000 + 1000);
            logger.info("#### random : {}", rand_num);
            
            // 랜덤 수 중복 체크.
            if(code_list.contains(rand_num)) {
                i--;
                continue;
            }
            code_list.add(rand_num);
            
            institute_map.put(bank_list.get(i), "bnk" + rand_num);
        }
        
        for(String key : institute_map.keySet()) {
            logger.info("#### name : {}, code : {}", key, institute_map.get(key));
        }
    }
    
    
    
    @Test
    public void pathTest() throws Exception {
    	String path = "csvdata/3.csv";
    	ClassPathResource cpr = new ClassPathResource(path);
    	byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
    	String jsonTxt = new String(bdata, StandardCharsets.UTF_8);
    	logger.info(jsonTxt);
    	
    }
    
    
    @Test
    public void readCSVFileTest() throws Exception {
    	String path = "csvdata/3.csv";
    	ClassPathResource cpr = new ClassPathResource(path);
    	
    	List<List<String>> records = new ArrayList<List<String>>();
    	CSVReader csvReader;
		try {
			FileInputStream csvFile = new FileInputStream(cpr.getFile());
			InputStreamReader readFile = new InputStreamReader(csvFile, "EUC-KR"); 
			csvReader = new CSVReader(readFile);
	
			String[] values = csvReader.readNext();
			records.add(Arrays.asList(values));
			
			/*
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {
				records.add(Arrays.asList(values));
	    	}
	    	*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		logger.info(records.toString());
    }
    

    @Test
    public void financeRepoTest() {
        //given
        repository.save(Finance.builder()
                .year(2005)
                .month(1)
                .institute_code("bnk1111")
                .value(1000)
                .build());

        //when
        List<Finance> srcList = repository.findAll();

        //then
        Finance finance = srcList.get(0);
        assertThat(finance.getYear(), is(2005));
        assertThat(finance.getMonth(), is(1));
    }
    
	
}


