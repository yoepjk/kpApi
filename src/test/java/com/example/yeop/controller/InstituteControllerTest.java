package com.example.yeop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.yeop.service.FinanceService;
import com.example.yeop.service.InstituteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(InstituteController.class)
public class InstituteControllerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
    private FinanceService financeService;
	
	@MockBean
    private InstituteService InstituteService;
	
	@Test
    public void initDataTest() throws Exception {
    	mvc.perform(get("/institute/init"))
    	.andExpect(status().isOk());
    }
	
	@Test
    public void instituteListTest() throws Exception {
    	mvc.perform(get("/institute/list"))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("name"))
    	.andExpect(model().attributeExists("data"));
    }
	
}
