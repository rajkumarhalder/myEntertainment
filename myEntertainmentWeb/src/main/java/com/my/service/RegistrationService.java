package com.my.service;

import com.my.model.MemberDetails;

public interface RegistrationService {
	
	public Object memberRegistration(Object object);

	public Object getAll();

	MemberDetails executeLogin(String userName, String passWord);

	MemberDetails getMemberByMemberId(Long memberId);

}
