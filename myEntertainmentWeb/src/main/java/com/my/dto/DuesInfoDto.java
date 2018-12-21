package com.my.dto;

import java.util.Map;

public class DuesInfoDto {
	private Long userId;
	private String fullName;
	private Map monthVsAmount;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Map getMonthVsAmount() {
		return monthVsAmount;
	}
	public void setMonthVsAmount(Map monthVsAmount) {
		this.monthVsAmount = monthVsAmount;
	}

}
