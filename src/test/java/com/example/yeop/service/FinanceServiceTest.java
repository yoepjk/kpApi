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

import com.example.yeop.entity.Finance;
import com.example.yeop.repository.FinanceRepository;
import com.example.yeop.service.impl.FinanceServiceImpl;
import com.example.yeop.vo.TotalByYear;

import lombok.val;

public class FinanceServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Mock
	private FinanceRepository mockRepository;

	@InjectMocks
	private FinanceServiceImpl mockService;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllTest() throws Exception {
		List<Finance> list = new ArrayList<>();
		list.add(new Finance(2019, 10, "code1", 100));
		
		when(mockService.getAll())
		.thenReturn(list);
		
		val result = mockService.getAll();
		
		assertNotNull(result);
		assertEquals(Integer.valueOf(2019), result.get(0).getYear());
		assertEquals(Integer.valueOf(10), result.get(0).getMonth());
		assertEquals("code1", result.get(0).getInstituteCode());
		assertEquals(Integer.valueOf(100), result.get(0).getValue());
	}
	
	@Test
	public void getTotalByYearTest() {
		List<TotalByYear> list = new ArrayList<>();
		list.add(new TotalByYear(2019, "name1", 200));
		
		when(mockService.getTotalByYear())
		.thenReturn(list);
		
		val result = mockService.getTotalByYear();
		
		assertNotNull(result);
		assertEquals(Integer.valueOf(2019), result.get(0).getYear());
		assertEquals("name1", result.get(0).getName());
		assertEquals(Integer.valueOf(200), result.get(0).getValue());
	}
	
	@Test
	public void getTotalByYearAndNameTest() {
		List<TotalByYear> list = new ArrayList<>();
		list.add(new TotalByYear(2019, "name2", 400));
		
		when(mockService.getTotalByYearAndName("name2", 2019, 300))
		.thenReturn(list);
		
		val result = mockService.getTotalByYearAndName("name2", 2019, 300);
		
		assertNotNull(result);
		assertEquals(Integer.valueOf(2019), result.get(0).getYear());
		assertEquals("name2", result.get(0).getName());
		assertEquals(Integer.valueOf(400), result.get(0).getValue());
	}

}
