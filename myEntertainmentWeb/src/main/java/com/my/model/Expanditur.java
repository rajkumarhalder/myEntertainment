package com.my.model;

import java.math.BigDecimal;
import java.util.Date;

public class Expanditur {

	private Long monthId;
	private Long expanditurId;

	private BigDecimal amount;

	private Date expanditurDate;

	private String purpose;

	private String shortDesc;

	
	
	public Long getMonthId() {
		return monthId;
	}

	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}

	public Long getExpanditurId() {
		return expanditurId;
	}

	public void setExpanditurId(Long expanditurId) {
		this.expanditurId = expanditurId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	

	public Date getExpanditurDate() {
		return expanditurDate;
	}

	public void setExpanditurDate(Date expanditurDate) {
		this.expanditurDate = expanditurDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}


}
