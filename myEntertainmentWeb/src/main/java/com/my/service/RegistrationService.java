package com.my.service;

import java.util.List;

import com.my.model.LoginInfo;
import com.my.model.MemberDetails;
import com.my.model.MonthMaster;
import com.my.model.Payments;

public interface RegistrationService {
	
	public Object memberRegistration(Object object);

	public Object getAll();

	MemberDetails executeLogin(String userName, String passWord);

	MemberDetails getMemberByMemberId(Long memberId);

	void insertPayment(Long memberId);

	List<Payments> getPaymentsByMemberId(Long memberId);

	List<MonthMaster> getMonthMaster();

	void updatePayment(Payments payments, LoginInfo info);

	

}
