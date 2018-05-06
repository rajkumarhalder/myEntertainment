package com.my.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.my.model.Member;

@Repository
public interface RegistrationRepository extends MongoRepository<Member, Long>{

	
}
