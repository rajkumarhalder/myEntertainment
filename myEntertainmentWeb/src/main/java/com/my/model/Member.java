package com.my.model;


import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="member")
public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7745635932611882507L;
	
	
	@Id
	private Long memberId;
	private String memberName;
	private String domainId;
	private String emailId;
	private String mobileNo;
	private String deskPhone;
	private String dateOfBirth;
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDeskPhone() {
		return deskPhone;
	}
	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
	
}
