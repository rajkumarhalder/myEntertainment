package com.my.dto;

import java.util.List;

import com.my.model.MemberDetails;

public class EntertainmentMailObject {
	
	private String mailBody;
	private String mailSubject;
	private List<MemberDetails> memberDetails;

	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public List<MemberDetails> getMemberDetails() {
		return memberDetails;
	}
	public void setMemberDetails(List<MemberDetails> memberDetails) {
		this.memberDetails = memberDetails;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	
	

}
