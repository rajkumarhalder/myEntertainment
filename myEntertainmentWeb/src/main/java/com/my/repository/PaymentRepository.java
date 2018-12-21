package com.my.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.my.model.Payments;

@Repository
public interface PaymentRepository extends CrudRepository<Payments, Long>{

	public List findByUserIdAndIsActive(Long memberId,int isActive);

	public List findByUserIdInAndIsActive(List<Long> memberIds,int isActive);
	
	public List findByUserIdInAndPaymentStatusInAndYearAndIsActive(List<Long> memberIds,List<Integer> paymentStatus,int year,int isActive);

	public List findByUserIdAndMonthIdAndYearAndIsActive(Long memberId,Long monthId,int year,int isActive);

	public List findByIsActive(int isActive);

}
