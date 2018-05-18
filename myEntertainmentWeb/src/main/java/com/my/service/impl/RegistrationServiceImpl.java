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
		
		member.setMemberId(sequenceDao.getNextSequenceId("member_seq"));
		//member.setMemberId(1000L);
		
		registrationRepository.save(member);
		
		return "success";
	}
	
	@Override
	public Object getAll() {
		
		return registrationRepository.findAll();
	}
	
	@Override
	public MemberDetails executeLogin(String userName,String passWord) {
		
		MemberDetails memberDetails=null;
		try {
			memberDetails=registrationRepository.findByUserNamePassword(userName, passWord);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberDetails;
	}
	
	@Override
	public MemberDetails getMemberByMemberId(Long memberId) {
		
		MemberDetails memberDetails=null;
		try {
			memberDetails=registrationRepository.findMemberByMemberId(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberDetails;
	}
	
	
	
	
	

}
