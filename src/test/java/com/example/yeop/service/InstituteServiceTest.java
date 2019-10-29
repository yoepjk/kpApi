package com.example.yeop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.yeop.entity.Institute;
import com.example.yeop.repository.InstituteRepository;
import com.example.yeop.service.impl.InstituteServiceImpl;

import lombok.val;

public class InstituteServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Mock
	private InstituteRepository mockRepository;
	
	@InjectMocks
	private InstituteServiceImpl mockService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllTest() {
		List<Institute> list = new ArrayList<>();
		list.add(new Institute("name1", "code1"));
		
		when(mockService.getAll())
		.thenReturn(list);
		
		val result = mockService.getAll();
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("name1", result.get(0).getInstituteName());
		assertEquals("code1", result.get(0).getInstituteCode());
	}
	 
}
