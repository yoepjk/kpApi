package com.example.yeop.utils;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomUtilTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void getRandNum() {
		// 두 자리수의 랜덤 수 추출
		int min = 10;
		int max = 99;
		
		Integer result = (int) (Math.random() * (max-min) + min);
		logger.info("#### result : {}", result);
		
		assertTrue("10보다 큰지", min < result);
		assertTrue("99보다 작은지", max > result);
	}
}
