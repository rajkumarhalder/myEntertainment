package com.my.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.my.model.MemberDetails;
import com.my.model.Payments;

@Repository
public interface PaymentRepository extends MongoRepository<Payments, Long>{
	
	@Query("{ 'memberId' : ?0}")
	public List getPaymentsByMember(Long memberId);
	
	@Query("{ 'memberId' : ?0 ,'monthId' : ?1}")
	public List getPaymentsByMemberandMonth(Long memberId,Long monthId);

}
