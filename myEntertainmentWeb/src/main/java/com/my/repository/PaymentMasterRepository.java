package com.my.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.my.model.PaymentMaster;

public interface PaymentMasterRepository extends CrudRepository<PaymentMaster, Long>{
	
	public List findByIsActive(int isActive);
	
	public List findByYearAndIsActive(int year,int isActive);
	
	public List findByYearAndMonthIdAndIsActive(int year,Long monthId,int isActive);

}
