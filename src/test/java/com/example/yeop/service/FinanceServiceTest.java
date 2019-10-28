package com.example.yeop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.yeop.repository.FinanceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinanceServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FinanceRepository finRepository;

	@After(value = "")
	public void cleanup() {
		finRepository.deleteAll();
	}

	@Test
	public void createAll() throws Exception {

	}
	
	

}
