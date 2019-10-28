package com.example.yeop.utils;

public class RandomUtil {
	
	public static Integer getRandNum(int min, int max) {
		return (int) (Math.random() * (max-min) + min);
	}

}
