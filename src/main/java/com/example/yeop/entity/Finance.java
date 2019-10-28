package com.example.yeop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Finance {
	@Id
	@GeneratedValue
    private Integer id;	
	@Column(columnDefinition = "integer default 0")
	private Integer year;
	
	@Column(columnDefinition = "integer default 0")
	private Integer month;
	
	@Column
	private String institute_code;
	
	@Column
	private Integer value;
	
	@Builder
    public Finance(Integer year, Integer month, String institute_code, Integer value) {
        this.year = year;
        this.month = month;
        this.institute_code = institute_code;
        this.value = value;
    }
}
