package com.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.SequenceDao;
import com.my.model.Member;
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
		Member member=null;
		if(object instanceof Member)
			member=(Member) object;
		
		member.setMemberId(sequenceDao.getNextSequenceId("members"));
		
		registrationRepository.save(member);
		
		return "success";
	}
	
	@Override
	public Object getAll() {
		
		return registrationRepository.findAll();
	}
	
	

}
