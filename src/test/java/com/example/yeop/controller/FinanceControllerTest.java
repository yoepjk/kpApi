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
import com.fasterxml.jackson.databind.ObjectMapper;

/*
@RunWith(SpringRunner.class)
@WebMvcTest(FinanceController.class)
public class FinanceControllerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;
	
    @MockBean
    private FinanceService financeService;
    
    @Test
    public void totalByYearTest() throws Exception {
    	mvc.perform(get("/finance/total-year"))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("name"))
    	.andExpect(model().attributeExists("data"));
    }
    
    @Test
    public void maxByYearTest() throws Exception {
    	mvc.perform(get("/finance/max-year"))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("year"))
    	.andExpect(model().attributeExists("bank"));
    }
    
    @Test
    public void averageMinMaxTest() throws Exception {
    	mvc.perform(get("/finance/avg-minmax-year"))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("bank"))
    	.andExpect(model().attributeExists("support_amount"));
    }
    
}
*/
