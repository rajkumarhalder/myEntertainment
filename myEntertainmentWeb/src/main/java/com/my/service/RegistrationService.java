package com.my.service;

import java.math.BigDecimal;
import java.util.List;

import com.my.model.Expanditur;
import com.my.model.LoginInfo;
import com.my.model.MemberDetails;
import com.my.model.MonthMaster;
import com.my.model.Notification;
import com.my.model.Payments;

public interface RegistrationService {

	public Object memberRegistration(MemberDetails member);

	public Object getAll();

	MemberDetails executeLogin(String userName, String passWord);

	MemberDetails getMemberByMemberId(Long memberId);

	void insertPayment(Long memberId);

	List<Payments> getPaymentsByMemberId(Long memberId);

	List<MonthMaster> getMonthMaster();

	void updatePayment(Payments payments, LoginInfo info);

	void updateProfile(MemberDetails memberDetails, LoginInfo info);

	List<Payments> getDuePayments(Long memberId);

	List<Notification> getNotification();

	BigDecimal geCurrentFundBalance();

	List<Expanditur> getExpanditure();

	BigDecimal geTotalEarning();

	void insertExpanditur(Expanditur expanditur);

	MemberDetails getMemberByUserCode(String userCode);

	void sendMail(String allUserMailFlag, Long targetUserId);


}
