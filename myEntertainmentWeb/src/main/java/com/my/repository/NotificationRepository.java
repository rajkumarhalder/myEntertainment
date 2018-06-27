package com.my.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.my.model.MonthMaster;
import com.my.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, Long> {

}
