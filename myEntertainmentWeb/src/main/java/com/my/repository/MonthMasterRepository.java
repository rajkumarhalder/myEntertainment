package com.my.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.my.model.MonthMaster;

@Repository
public interface MonthMasterRepository extends CrudRepository<MonthMaster, Long>{
	
	public Object findByIsActive(int isActive);

}
