package com.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.SequenceDao;
import com.my.model.MemberDetails;
import com.my.repository.RegistrationRepository;
import com.my.service.RegistrationService;


@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	SequenceDao sequenceDao;

	@Override
	public Object memberRegistration(Object object) {
		MemberDetails member=null;
		if(object instanceof MemberDetails)
			member=(MemberDetails) object;
		
		//member.setMemberId(sequenceDao.getNextSequenceId("members"));
		member.setMemberId(1000L);
		
		registrationRepository.save(member);
		
		return "success";
	}
	
	@Override
	public Object getAll() {
		
		return registrationRepository.findAll();
	}
	
	@Override
	public void executeLogin(String userName,String passWord) {
		
		
		try {
			MemberDetails memberDetails=registrationRepository.findByUserNamePassword(userName, passWord);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
