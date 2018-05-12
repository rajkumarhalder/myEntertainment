package com.my.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.my.model.MemberDetails;

@Repository
public interface RegistrationRepository extends MongoRepository<MemberDetails, Long>{

	@Query("{ 'userName' : ?0 ,'passWord' :?1}")
	public MemberDetails findByUserNamePassword(String userName,String passWord);


}
