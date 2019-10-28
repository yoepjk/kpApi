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
public class Institute {
	@Id
	@GeneratedValue
    private Integer id;	
	
	@Column
	private String institute_name;
	
	@Column
	private String institute_code;
	
	@Builder
	public Institute(String name, String code) {
		institute_name = name;
		institute_code = code;
	}
}