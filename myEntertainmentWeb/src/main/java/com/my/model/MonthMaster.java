package com.my.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="month_master")
public class MonthMaster {
	
	@Id
	private Long monthId;
	private String month;
	public Long getMonthId() {
		return monthId;
	}
	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	
	

}
