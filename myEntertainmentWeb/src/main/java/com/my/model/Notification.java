package com.my.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="notification")
public class Notification {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENTS_SEQ")
    @SequenceGenerator(sequenceName = "entmt.notification_seq", allocationSize = 1, name = "NOTIFICATION_SEQ")
	private Long Id;
	private String notificationMsg;
	private Long notificationFrom;
	private Long notificationTo;
	private Integer isActive;
	private Long createId;
	private Date createDate;
	private Long updateId;
	private Date updateDate;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNotificationMsg() {
		return notificationMsg;
	}
	public void setNotificationMsg(String notificationMsg) {
		this.notificationMsg = notificationMsg;
	}
	public Long getNotificationFrom() {
		return notificationFrom;
	}
	public void setNotificationFrom(Long notificationFrom) {
		this.notificationFrom = notificationFrom;
	}
	public Long getNotificationTo() {
		return notificationTo;
	}
	public void setNotificationTo(Long notificationTo) {
		this.notificationTo = notificationTo;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
}
