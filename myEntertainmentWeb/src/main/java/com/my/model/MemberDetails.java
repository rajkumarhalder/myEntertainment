package com.my.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.my.util.EntertainmentConstant;

@Document(collection="member")
public class MemberDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7745635932611882507L;
	
	
	@Id
	private Long memberId;
	private Integer roleId;
	private String name;
	private String password;
	private String emailId;
	private String mobileNumber;
	private String deskPhoneNumber;
	private Date dateOfBirth;
	private String username;
	
	@Transient
	private List<Payments> payments;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDeskPhoneNumber() {
		return deskPhoneNumber;
	}

	public void setDeskPhoneNumber(String deskPhoneNumber) {
		this.deskPhoneNumber = deskPhoneNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Payments> getPayments() {
		return payments;
	}

	public void setPayments(List<Payments> payments) {
		this.payments = payments;
	}
	
	

	
}
