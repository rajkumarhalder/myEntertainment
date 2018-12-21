package com.my.repository;

import org.springframework.data.repository.CrudRepository;

import com.my.model.MailNotification;

public interface MailNotificationRepository extends CrudRepository<MailNotification, Long>{

	public Object findByeventIdAndIsActive(Long eventId,int isActive);
	public Object findByIsActive(int isActive);


}
