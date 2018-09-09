package com.my.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.my.model.MailNotification;

public interface MailNotificationRepository extends MongoRepository<MailNotification, Long>{

public MailNotification findByMailNotificationId(Long id);
}
