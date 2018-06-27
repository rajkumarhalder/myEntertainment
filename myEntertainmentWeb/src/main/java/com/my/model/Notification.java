package com.my.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="notification")
public class Notification {

	
	private String notificationMsg;

	public String getNotificationMsg() {
		return notificationMsg;
	}

	public void setNotificationMsg(String notificationMsg) {
		this.notificationMsg = notificationMsg;
	}
	
	
	
}
