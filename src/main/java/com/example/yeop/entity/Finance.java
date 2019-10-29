package com.example.yeop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	
	@Column(name="institute_code")
	private String instituteCode;
	
	@Column
	private Integer value;
	
	@OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="code", insertable = false)
	private Institute institute;
	

	//	@ManyToMany(cascade = CascadeType.ALL)
//	private List<Institute> institutes;
	
	@Builder
    public Finance(Integer year, Integer month, String instituteCode, Integer value) {
        this.year = year;
        this.month = month;
        this.instituteCode = instituteCode;
        this.value = value;
    }
}
