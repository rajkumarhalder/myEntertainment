package com.my.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.my.model.MemberDetails;

@Repository
public interface MemberRepository extends CrudRepository<MemberDetails, Long>{

	public List findByUsernameAndIsActive(String userName,int isActive);

	public List findByIdAndIsActive(Long memberId,int isActive);
	
	public List findByIsActive(int isActive);
	
	public List findByIsActiveAndUserStatus(int isActive,int status);

}
