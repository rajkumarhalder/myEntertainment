package com.my.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.my.model.Expenditure;

public interface ExpanditurRepository extends CrudRepository<Expenditure, Long>{

	public List findByMonthIdAndYearAndIsActive(Long monthId,Long year,int isActive);

	public List findByYearAndIsActive(Long year,int isActive);

	public List findByIsActive(int isActive);

}
