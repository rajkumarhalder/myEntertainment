package com.my.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mail_notification")
public class MailNotification implements Serializable{
	
	@Id
	private Long mailNotificationId;
	private String mailSubject;
	private String mailBody;
	public Long getMailNotificationId() {
		return mailNotificationId;
	}
	public void setMailNotificationId(Long mailNotificationId) {
		this.mailNotificationId = mailNotificationId;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

}
