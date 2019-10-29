package com.example.yeop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class Institute {
	@Id
	@GeneratedValue
    private Integer id;	
	
	@Column(name="institute_name")
	private String instituteName;
	
	@Column(name="institute_code")
	private String instituteCode;
	
	@Builder
	public Institute(String name, String code) {
		instituteName = name;
		instituteCode = code;
	}
}