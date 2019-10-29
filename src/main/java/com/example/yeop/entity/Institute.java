package com.example.yeop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	
	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	@Builder
	public Institute(String name, String code) {
		this.name = name;
		this.code = code;
	}
}