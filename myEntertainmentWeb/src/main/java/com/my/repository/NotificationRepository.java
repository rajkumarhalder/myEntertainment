package com.my.repository;

import org.springframework.data.repository.CrudRepository;

import com.my.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

	public Object findByIsActive(int isActive);
}
