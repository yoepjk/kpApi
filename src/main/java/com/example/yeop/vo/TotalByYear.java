package com.example.yeop.vo;

import java.io.Serializable;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class TotalByYear implements Serializable {
	private static final long serialVersionUID = -3777937207533558441L;
	
	private Integer year;
	private String name;
	private Integer value;
	
	@QueryProjection
	public TotalByYear(Integer year, String name, Integer value) {
		this.year = year;
		this.name = name;
		this.value = value;
	}
}
